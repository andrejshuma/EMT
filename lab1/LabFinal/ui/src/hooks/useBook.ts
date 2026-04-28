import { useEffect, useState } from 'react'
import type { Book } from '../types'
import { booksRepository } from '../repositories/booksRepository'

export function useBook(id: number | null) {
    const [data, setData] = useState<Book | null>(null)
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

        booksRepository.findById(id)
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [id])

    return { data, loading, error }
}
