import { Link as RouterLink } from 'react-router-dom'
import { Alert, CircularProgress, List, ListItemButton, ListItemText, Typography } from '@mui/material'
import { useAuthors } from '../hooks/useAuthors'

export function AuthorsPage() {
    const { data, loading, error } = useAuthors()

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>Authors</Typography>
            <List>
                {data.map((a) => (
                    <ListItemButton key={a.id} component={RouterLink} to={`/authors/${a.id}`}>
                        <ListItemText
                            primary={`${a.name}${a.surname ? ` ${a.surname}` : ''}`}
                            secondary={a.country ? `Country: ${a.country}` : undefined}
                        />
                    </ListItemButton>
                ))}
            </List>
        </div>
    )
}
