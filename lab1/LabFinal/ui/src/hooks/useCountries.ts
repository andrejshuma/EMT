import { useEffect, useState } from 'react'
import type { Country } from '../types'
import { countriesRepository } from '../repositories/countriesRepository.ts'

export function useCountries() {
    const [data, setData] = useState<Country[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        let mounted = true
        setLoading(true)
        setError(null)

        countriesRepository.findAll()
            .then((res) => mounted && setData(res))
            .catch((e) => mounted && setError(e?.message ?? String(e)))
            .finally(() => mounted && setLoading(false))

        return () => { mounted = false }
    }, [])

    return { data, loading, error }
}
