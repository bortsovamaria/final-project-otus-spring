import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {addTask, getTask, updateTask} from "../services/TaskService";

const TaskComponent = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("");
  const [status, setStatus] = useState("");
  const [assignedTo, setAssignedTo] = useState("");

  const navigator = useNavigate();

  const { id } = useParams();

  const saveOrUpdateTask = (e) => {
    e.preventDefault();

    const task = { title, description, status, priority, assignedTo };

    if (id) {
      updateTask(id, task)
        .then((response) => {
          console.log(response.data);
          navigator("/tasks");
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      addTask(task)
        .then((response) => {
          console.log(response.data);
          navigator("/tasks");
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update</h2>;
    } else {
      return <h2 className="text-center">Add</h2>;
    }
  };

  useEffect(() => {
    if (id) {
      getTask(id)
        .then((response) => {
          setTitle(response.data.title);
          setDescription(response.data.description);
          setPriority(response.data.priority)
          setStatus(response.data.status)
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [id]);

  return (
    <div className="container">
      <br />
      <br />
      <div className="row">
        <div className="card col-md-6 offset-md-3 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">Todo Title:</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Enter Todo Title:"
                    name="title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                ></input>
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Todo Description:</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Enter Todo Description:"
                    name="description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                ></input>
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Status:</label>
                <input
                    type="number"
                    className="form-control"
                    placeholder="Enter Todo Description:"
                    name="status"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                ></input>
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Priority:</label>
                <input
                    type="number"
                    className="form-control"
                    placeholder="Enter Todo Description:"
                    name="priority"
                    value={priority}
                    onChange={(e) => setPriority(e.target.value)}
                ></input>
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Assigned to:</label>
                <input
                    type="number"
                    className="form-control"
                    placeholder="Enter Todo Description:"
                    name="assignedTo"
                    value={assignedTo}
                    onChange={(e) => setAssignedTo(e.target.value)}
                ></input>
              </div>

              <button
                  className="btn btn-primary btn-success"
                  onClick={(e) => {
                    saveOrUpdateTask(e);
                  }}
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TaskComponent;
