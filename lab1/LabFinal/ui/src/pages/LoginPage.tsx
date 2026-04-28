import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { api } from '../api/axios'
import { setToken } from '../auth'

type LoginResponse = {
    tokenType: string
    accessToken: string
    expiresIn: number
    expiresAt: string | null
    username: string
    roles: string[]
}

export function LoginPage() {
    const navigate = useNavigate()

    const [username, setUsername] = useState('admin')
    const [password, setPassword] = useState('admin123')
    const [error, setError] = useState<string | null>(null)
    const [loading, setLoading] = useState(false)

    async function onSubmit(e: React.FormEvent) {
        e.preventDefault()
        setError(null)
        setLoading(true)

        try {
            const res = await api.post<LoginResponse>('/auth/login', { username, password })
            setToken(res.data.accessToken)
            navigate('/books')
        } catch (err: any) {
            setError(err?.message ?? String(err))
        } finally {
            setLoading(false)
        }
    }

    return (
        <div>
            <h1>Login</h1>

            <form onSubmit={onSubmit} style={{ display: 'grid', gap: 8, maxWidth: 320 }}>
                <label>
                    Username
                    <input value={username} onChange={(e) => setUsername(e.target.value)} />
                </label>

                <label>
                    Password
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>

                <button type="submit" disabled={loading}>
                    {loading ? 'Logging in...' : 'Login'}
                </button>
            </form>

            {error && <p style={{ color: 'crimson' }}>Error: {error}</p>}

            <p style={{ marginTop: 12 }}>
                Test users: <code>admin/admin123</code> or <code>user/user123</code>
            </p>
        </div>
    )
}
