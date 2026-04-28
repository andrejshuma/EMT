import { useEffect, useState } from 'react'
import type { Author } from '../types'
import { authorsRepository } from '../repositories/authorsRepository.ts'

export function useAuthor(id: number | null) {
    const [data, setData] = useState<Author | null>(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        if (id == null || Number.isNaN(id)) {
            setData(null)
            setLoading(false)
            setError('Invalid id')
            return
        }

        let mounted = true
        setLoading(true)
        setError(null)

        authorsRepository.findById(id)
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [id])

    return { data, loading, error }
}
