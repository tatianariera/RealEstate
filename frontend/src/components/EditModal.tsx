import React, { useState, useEffect } from "react";

type EditModalProps = {
  isOpen: boolean;
  initialName: string;
  initialEmail: string;
  onSave: (name: string, email: string) => void;
  onCancel: () => void;
};

export default function EditModal({
  isOpen,
  initialName,
  initialEmail,
  onSave,
  onCancel,
}: EditModalProps) {
  const [name, setName] = useState(initialName);
  const [email, setEmail] = useState(initialEmail);

  useEffect(() => {
    if (isOpen) {
      setName(initialName);
      setEmail(initialEmail);
    }
  }, [isOpen, initialName, initialEmail]);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 backdrop-blur-md bg-opacity-30 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-96 shadow-lg">
        <h3 className="text-xl font-semibold mb-4">Edit Employee</h3>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            onSave(name, email);
          }}
          className="space-y-4"
        >
          <div>
            <label className="block mb-1 font-medium">Name</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
              className="w-full border rounded px-3 py-2"
            />
          </div>
          <div>
            <label className="block mb-1 font-medium">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="w-full border rounded px-3 py-2"
            />
          </div>
          <div className="flex justify-end gap-3 pt-4">
            <button
              type="button"
              onClick={onCancel}
              className="px-4 py-2 rounded bg-gray-300 hover:bg-gray-400"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 rounded bg-blue-400 text-white hover:bg-blue-600"
            >
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
