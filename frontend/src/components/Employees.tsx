import { useEffect, useState } from "react";
import ConfirmModal from "../components/ConfirmModal";
import EditModal from "../components/EditModal";

type Employee = {
  id: number;
  name: string;
  email: string;
};

export default function Employees() {
  const [employees, setEmployees] = useState<Employee[]>([]);
  const [newName, setNewName] = useState("");
  const [newEmail, setNewEmail] = useState("");
  const [showConfirm, setShowConfirm] = useState(false);
  const [employeeToDelete, setEmployeeToDelete] = useState<number | null>(null);

  // Para editar con modal:
  const [showEditModal, setShowEditModal] = useState(false);
  const [editEmployeeId, setEditEmployeeId] = useState<number | null>(null);
  const [editName, setEditName] = useState("");
  const [editEmail, setEditEmail] = useState("");

  const API = "http://localhost:8081/api/employees";

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    const res = await fetch(API);
    const data = await res.json();
    setEmployees(data);
  };

  const openEditModal = (id: number, currentName: string, currentEmail: string) => {
    setEditEmployeeId(id);
    setEditName(currentName);
    setEditEmail(currentEmail);
    setShowEditModal(true);
  };

  const handleEditSave = async (name: string, email: string) => {
    if (editEmployeeId === null) return;
    await fetch(`${API}/${editEmployeeId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, email }),
    });
    setShowEditModal(false);
    setEditEmployeeId(null);
    fetchEmployees();
  };

  const confirmDelete = (id: number) => {
    setEmployeeToDelete(id);
    setShowConfirm(true);
  };

  const handleDeleteConfirmed = async () => {
    if (employeeToDelete !== null) {
      await fetch(`${API}/${employeeToDelete}`, { method: "DELETE" });
      fetchEmployees();
      setEmployeeToDelete(null);
      setShowConfirm(false);
    }
  };

  const handleCreate = async (e: React.FormEvent) => {
    e.preventDefault();
    await fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: newName, email: newEmail }),
    });
    setNewName("");
    setNewEmail("");
    fetchEmployees();
  };

  return (
    <div>
      <div className="space-y-4 mb-8">
        {employees.map((emp) => (
          <div
            key={emp.id}
            className="border p-4 rounded shadow flex justify-between items-center"
          >
            <div>
              <p>
                <strong>{emp.name}</strong> ({emp.email})
              </p>
            </div>
            <div className="space-x-2">
              <button
                className="bg-blue-400 px-3 py-1 rounded hover:bg-blue-600 text-white"
                onClick={() => openEditModal(emp.id, emp.name, emp.email)}
              >
                Edit
              </button>
              <button
                className="bg-red-500 px-3 py-1 rounded hover:bg-red-600 text-white"
                onClick={() => confirmDelete(emp.id)}
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>

      <hr className="my-6" />

      <div>
        <h2 className="text-xl font-semibold mb-4">Add New Employee</h2>
        <form onSubmit={handleCreate} className="space-y-2 max-w-md mx-auto">
          <input
            type="text"
            placeholder="Name"
            required
            className="border p-2 rounded w-full"
            value={newName}
            onChange={(e) => setNewName(e.target.value)}
          />
          <input
            type="email"
            placeholder="Email"
            required
            className="border p-2 rounded w-full"
            value={newEmail}
            onChange={(e) => setNewEmail(e.target.value)}
          />
          <div className="flex justify-center">
            <button
              type="submit"
              className="bg-blue-400 text-white px-6 py-2 rounded hover:bg-blue-600 w-50 mt-3"
            >
              Create Employee
            </button>
          </div>
        </form>
      </div>

      {showConfirm && (
        <ConfirmModal
          message="Are you sure you want to delete this employee?"
          onConfirm={handleDeleteConfirmed}
          onCancel={() => setShowConfirm(false)}
        />
      )}

      <EditModal
        isOpen={showEditModal}
        initialName={editName}
        initialEmail={editEmail}
        onSave={handleEditSave}
        onCancel={() => setShowEditModal(false)}
      />
    </div>
  );
}
