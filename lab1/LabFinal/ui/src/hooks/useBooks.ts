import { useEffect, useState } from 'react'
import type { Book } from '../types'
import { booksRepository } from '../repositories/booksRepository'

export function useBooks() {
    const [data, setData] = useState<Book[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        let mounted = true
        setLoading(true)
        setError(null)

        booksRepository.findAll()
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [])

    return { data, loading, error }
}
