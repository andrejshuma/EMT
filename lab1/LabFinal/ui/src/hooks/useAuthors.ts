import { useEffect, useState } from 'react'
import type { Author } from '../types'
import { authorsRepository } from '../repositories/authorsRepository.ts'

export function useAuthors() {
    const [data, setData] = useState<Author[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        let mounted = true
        setLoading(true)
        setError(null)

        authorsRepository.findAll()
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [])

    return { data, loading, error }
}



