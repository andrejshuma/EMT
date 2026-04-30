import { Link, Outlet, useNavigate } from 'react-router-dom'
import { AppBar, Box, Button, Container, Toolbar, Typography } from '@mui/material'
import { clearToken, isLoggedIn } from './auth.ts'

export function App() {
    const navigate = useNavigate();

    const loggedIn = isLoggedIn()

    function logout() {
        clearToken();
        navigate('/login');
    }

    return (
        <Box sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
            <AppBar position="static">
                <Toolbar sx={{ gap: 2 }}>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        Library UI
                    </Typography>

                    <Button color="inherit" component={Link} to="/">Home</Button>
                    <Button color="inherit" component={Link} to="/books">Books</Button>
                    <Button color="inherit" component={Link} to="/authors">Authors</Button>
                    <Button color="inherit" component={Link} to="/countries">Countries</Button>
                    {!loggedIn ? (
                        <Button color="inherit" component={Link} to="/login">Login</Button>
                    ) : (
                        <Button color="inherit" onClick={logout}>
                            Logout
                        </Button>
                    )}
                </Toolbar>
            </AppBar>

            <Container sx={{ py: 3, flexGrow: 1 }}>
                <Outlet />
            </Container>

            <Box component="footer" sx={{ py: 2, borderTop: '1px solid #eee' }}>
                <Container>
                    <Typography variant="body2" color="text.secondary">
                        EMT Lab UI
                    </Typography>
                </Container>
            </Box>
        </Box>
    )
}
