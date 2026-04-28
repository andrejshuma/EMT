export type Country = {
    id: number
    name: string
    continent?: string
}

export type Author = {
    id: number
    name: string
    surname?: string
    country?: string
}

export type Book = {
    id: number
    name: string
    category?: string
    state?: string
    availableCopies?: number
}
