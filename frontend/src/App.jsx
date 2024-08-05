import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import {isUserLoggedIn} from "./services/AuthService";
import HeaderComponent from "./components/HeaderComponent";
import LoginComponent from "./components/LoginComponent";
import StartPage from "./components/StartPage";
import RegisterComponent from "./components/RegisterComponent";
import FooterComponent from "./components/FooterComponent";
import ListTasksComponent from "./components/ListTasksComponent";
import TaskComponent from "./components/TaskComponent";

function App() {
    const AuthenticatedRoute = ({ children }) => {
        const isAuth = isUserLoggedIn();

        if (isAuth) {
            return children;
        }

        return <Navigate to="/" />;
    };

    return (
        <>
            <HeaderComponent />
            <Routes>
                <Route path="/" element={<LoginComponent />} />
                <Route
                    path="/todos"
                    element={
                        <AuthenticatedRoute>
                            <StartPage />
                        </AuthenticatedRoute>
                    }
                />
                <Route
                    path="/tasks"
                    element={
                        <AuthenticatedRoute>
                            <ListTasksComponent />
                        </AuthenticatedRoute>
                    }
                />

                <Route
                    path="/add-task"
                    element={
                        <AuthenticatedRoute>
                            <TaskComponent />
                        </AuthenticatedRoute>
                    }
                />

                {/*<Route*/}
                {/*    path="/update-todo/:id"*/}
                {/*    element={*/}
                {/*        <AuthenticatedRoute>*/}
                {/*            <TodoComponent />*/}
                {/*        </AuthenticatedRoute>*/}
                {/*    }*/}
                {/*/>*/}

                <Route path="/register" element={<RegisterComponent />} />

                <Route path="/login" element={<LoginComponent />} />
            </Routes>
            <FooterComponent />
        </>
    );
}

export default App;