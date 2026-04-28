import { useParams } from 'react-router-dom'
import { Alert, CircularProgress, Typography } from '@mui/material'
import { useAuthor } from '../hooks/useAuthor'

export function AuthorDetailsPage() {
    const { id } = useParams()
    const authorId = id ? Number(id) : null
    const { data, loading, error } = useAuthor(authorId)

    if (loading) return <CircularProgress />
    if (error) return <Alert severity="error">{error}</Alert>
    if (!data) return <Alert severity="warning">Not found</Alert>

    return (
        <div>
            <Typography variant="h4" gutterBottom>
                {data.name}{data.surname ? ` ${data.surname}` : ''}
            </Typography>
            <Typography>Country: {data.country ?? '-'}</Typography>
        </div>
    )
}

