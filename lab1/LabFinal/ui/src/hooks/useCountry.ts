import { useEffect, useState } from 'react'
import type { Country } from '../types'
import { countriesRepository } from '../repositories/countriesRepository.ts'

export function useCountry(id: number | null) {
    const [data, setData] = useState<Country | null>(null)
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

        countriesRepository.findById(id)
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [id])

    return { data, loading, error }
}
