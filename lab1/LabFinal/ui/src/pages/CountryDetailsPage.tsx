import { useParams } from 'react-router-dom'
import { Alert, CircularProgress, Typography } from '@mui/material'
import { useCountry } from '../hooks/useCountry'

export function CountryDetailsPage() {
    const { id } = useParams()
    const countryId = id ? Number(id) : null
    const { data, loading, error } = useCountry(countryId)

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>
    if (!data) return <Alert severity="warning">Not found</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>{data.name}</Typography>
            <Typography>Continent: {data.continent ?? '-'}</Typography>
        </div>
    )
}

