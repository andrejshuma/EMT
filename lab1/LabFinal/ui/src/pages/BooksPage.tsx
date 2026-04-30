import { useMemo, useState } from 'react'
import { Link as RouterLink } from 'react-router-dom'
import {
    Alert,
    Box,
    CircularProgress,
    FormControl,
    InputLabel,
    List,
    ListItemButton,
    ListItemText,
    MenuItem,
    Select,
    type SelectChangeEvent,
    Typography,
} from '@mui/material'
import { useBooks } from '../hooks/useBooks'

export function BooksPage() {
    const { data, loading, error } = useBooks()

    const [category, setCategory] = useState('')
    const [state, setState] = useState('')

    const categories = useMemo(() => {
        return Array.from(
            new Set(data.map((b) => b.category).filter((x): x is string => Boolean(x && x.trim())))
        ).sort((a, b) => a.localeCompare(b))
    }, [data])

    const states = useMemo(() => {
        return Array.from(
            new Set(data.map((b) => b.state).filter((x): x is string => Boolean(x && x.trim())))
        ).sort((a, b) => a.localeCompare(b))
    }, [data])

    const filtered = useMemo(() => {
        return data.filter((b) => {
            if (category && b.category !== category) return false
            if (state && b.state !== state) return false
            return true
        })
    }, [data, category, state])

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>Books</Typography>

            <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', mb: 2 }}>
                <FormControl size="small" sx={{ minWidth: 220 }}>
                    <InputLabel id="books-category-label">Category</InputLabel>
                    <Select
                        labelId="books-category-label"
                        value={category}
                        label="Category"
                        onChange={(e: SelectChangeEvent) => setCategory(e.target.value)}
                    >
                        <MenuItem value="">All</MenuItem>
                        {categories.map((c) => (
                            <MenuItem key={c} value={c}>{c}</MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl size="small" sx={{ minWidth: 220 }}>
                    <InputLabel id="books-state-label">State</InputLabel>
                    <Select
                        labelId="books-state-label"
                        value={state}
                        label="State"
                        onChange={(e: SelectChangeEvent) => setState(e.target.value)}
                    >
                        <MenuItem value="">All</MenuItem>
                        {states.map((s) => (
                            <MenuItem key={s} value={s}>{s}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </Box>

            <List>
                {filtered.map((b) => (
                    <ListItemButton key={b.id} component={RouterLink} to={`/books/${b.id}`}>
                        <ListItemText
                            primary={b.name}
                            secondary={
                                [b.category ? `Category: ${b.category}` : null, b.state ? `State: ${b.state}` : null]
                                    .filter(Boolean)
                                    .join(' · ') || undefined
                            }
                        />
                    </ListItemButton>
                ))}
            </List>
        </div>
    )
}
