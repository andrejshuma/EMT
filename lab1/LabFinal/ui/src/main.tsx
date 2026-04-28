import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { App } from './App'
import { HomePage } from './pages/HomePage'
import { BooksPage } from './pages/BooksPage'
import { BookDetailsPage } from './pages/BookDetailsPage'
import { AuthorsPage } from './pages/AuthorsPage'
import { AuthorDetailsPage } from './pages/AuthorDetailsPage'
import { LoginPage } from './pages/LoginPage'
import { CountriesPage } from './pages/CountriesPage'
import { CountryDetailsPage } from './pages/CountryDetailsPage'

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        children: [
            { index: true, element: <HomePage /> },
            { path: 'login', element: <LoginPage /> },

            { path: 'books', element: <BooksPage /> },
            { path: 'books/:id', element: <BookDetailsPage /> },

            { path: 'authors', element: <AuthorsPage /> },
            { path: 'authors/:id', element: <AuthorDetailsPage /> },

            { path: 'countries', element: <CountriesPage /> },
            { path: 'countries/:id', element: <CountryDetailsPage /> },

        ],
    },
])

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>,
)
