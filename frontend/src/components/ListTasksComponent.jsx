import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {deleteTask, getAllTasks} from "../services/TaskService";

const ListTasksComponent = () => {
    const [tasks, setTasks] = useState([]);

    const navigator = useNavigate();

    useEffect(() => {
        getAllTasks().then((response) => {
            console.log(response)
            setTasks(response.data);
        });
    }, []);

    // POST new TODO
    const addNewTask = () => {
        navigator("/add-task");
    };

    // UPDATE TODO
    const updateTodo = (id) => {
        navigator(`/update-todo/${id}`);
    };

    const removeTodo = (id) => {
        deleteTask(id)
            .then((response) => {
                console.log(response.data);
                window.location.reload();
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <div className="container">
            <br/>
            <h2 className="text-center">List of Todos</h2>
            {
                <button
                    className="btn btn-primary mb-2"
                    onClick={() => {
                        addNewTask();
                    }}
                >
                    Add task
                </button>
            }

            <div>
                <table className="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>
                    {tasks.map((task) => (
                        <tr key={task.id}>
                            <td>{task.title}</td>
                            <td>{task.description}</td>
                            <td>
                                <>
                                    {(
                                        <button
                                            className="btn btn-info"
                                            onClick={() => {
                                                updateTodo(task.id);
                                            }}
                                        >
                                            Update
                                        </button>
                                    )}

                                    {(
                                        <button
                                            className="btn btn-danger"
                                            onClick={() => {
                                                removeTodo(task.id);
                                            }}
                                            style={{marginLeft: "10px"}}
                                        >
                                            Delete
                                        </button>
                                    )}
                                </>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ListTasksComponent;
