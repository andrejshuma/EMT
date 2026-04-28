import { useParams } from 'react-router-dom'
import { Alert, CircularProgress, Typography } from '@mui/material'
import { useBook } from '../hooks/useBook'

export function BookDetailsPage() {
    const { id } = useParams()
    const bookId = id ? Number(id) : null
    const { data, loading, error } = useBook(bookId)

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>
    if (!data) return <Alert severity="warning">Not found</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>{data.name}</Typography>
            <Typography>Category: {data.category ?? '-'}</Typography>
            <Typography>State: {data.state ?? '-'}</Typography>
            <Typography>Available copies: {data.availableCopies ?? '-'}</Typography>
        </div>
    )
}

