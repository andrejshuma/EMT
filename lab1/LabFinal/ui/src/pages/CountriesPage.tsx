import { Link as RouterLink } from 'react-router-dom'
import { Alert, CircularProgress, List, ListItemButton, ListItemText, Typography } from '@mui/material'
import { useCountries } from '../hooks/useCountries'

export function CountriesPage() {
    const { data, loading, error } = useCountries()

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>Countries</Typography>
            <List>
                {data.map((c) => (
                    <ListItemButton key={c.id} component={RouterLink} to={`/countries/${c.id}`}>
                        <ListItemText
                            primary={c.name}
                            secondary={c.continent ? `Continent: ${c.continent}` : undefined}
                        />
                    </ListItemButton>
                ))}
            </List>
        </div>
    )
}
