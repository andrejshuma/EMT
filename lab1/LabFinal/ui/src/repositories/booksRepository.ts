import { api } from '../api/axios'
import type { Book } from '../types'

export const booksRepository = {
    async findAll(): Promise<Book[]> {
        const res = await api.get<Book[]>('/books')
        return res.data
    },

    async findById(id: number): Promise<Book> {
        const res = await api.get<Book>(`/books/${id}`)
        return res.data
    },
}
