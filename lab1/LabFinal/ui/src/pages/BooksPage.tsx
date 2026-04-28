import { Link as RouterLink } from 'react-router-dom'
import { Alert, CircularProgress, List, ListItemButton, ListItemText, Typography } from '@mui/material'
import { useBooks } from '../hooks/useBooks'

export function BooksPage() {
    const { data, loading, error } = useBooks()

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>Books</Typography>
            <List>
                {data.map((b) => (
                    <ListItemButton key={b.id} component={RouterLink} to={`/books/${b.id}`}>
                        <ListItemText
                            primary={b.name}
                            secondary={b.category ? `Category: ${b.category}` : undefined}
                        />
                    </ListItemButton>
                ))}
            </List>
        </div>
    )
}
