import { api } from '../api/axios'
import type { Author } from '../types'

export const authorsRepository = {
    async findAll(): Promise<Author[]> {
        const res = await api.get<Author[]>('/authors')
        return res.data
    },

    async findById(id: number): Promise<Author> {
        const res = await api.get<Author>(`/authors/${id}`)
        return res.data
    },
}
