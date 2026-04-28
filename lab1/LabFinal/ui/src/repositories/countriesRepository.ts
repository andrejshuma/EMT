import { api } from '../api/axios'
import type { Country } from '../types'

export const countriesRepository = {
    async findAll(): Promise<Country[]> {
        const res = await api.get<Country[]>('/countries')
        return res.data
    },

    async findById(id: number): Promise<Country> {
        const res = await api.get<Country>(`/countries/${id}`)
        return res.data
    },
}
