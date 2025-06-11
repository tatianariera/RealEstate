import React, { useState } from "react";

export default function LoginForm() {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  async function handleLogin(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const form = e.currentTarget;
    const formData = new FormData(form);
    const email = formData.get("email");
    const password = formData.get("password");

    try {
      const res = await fetch("http://localhost:8081/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      const msg = await res.text();
      if (res.ok) {
localStorage.setItem("loggedIn", "true");
        setSuccess(msg);
        setError("");
        form.reset();
        // Optionally redirect or do something on successful login
      } else {
        setError(msg);
        setSuccess("");
      }
    } catch (err) {
      setError("Network error. Is the backend running?");
      setSuccess("");
    }
  }

  return (
    <form
      onSubmit={handleLogin}
      className="bg-white rounded-xl shadow-lg p-8 space-y-6 text-center"
      aria-label="Login form"
    >
      <h2 className="text-4xl font-extrabold text-gray-800">Sign In</h2>

      {error && (
        <p className="text-red-600 bg-red-100 border border-red-300 rounded-md px-4 py-2">
          {error}
        </p>
      )}

      {success && (
        <p className="text-green-700 bg-green-100 border border-green-300 rounded-md px-4 py-2">
          {success}
        </p>
      )}

      <div className="flex flex-col gap-2 text-left">
        <label htmlFor="email" className="font-semibold text-gray-700">
          Email
        </label>
        <input
          type="email"
          id="email"
          name="email"
          placeholder="example@mail.com"
          required
          className="border border-gray-300 rounded-md px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
          autoComplete="email"
        />
      </div>

      <div className="flex flex-col gap-2 text-left">
        <label htmlFor="password" className="font-semibold text-gray-700">
          Password
        </label>
        <input
          type="password"
          id="password"
          name="password"
          placeholder="••••••••"
          required
          className="border border-gray-300 rounded-md px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
          autoComplete="current-password"
        />
      </div>

      <button
        type="submit"
        className="bg-gray-600 hover:bg-blue-400 text-white font-semibold rounded-md py-3 text-lg transition-colors duration-300 w-35"
      >
        Log In
      </button>

      <p className="text-sm text-gray-600 mt-4">
        Don’t have an account?{" "}
        <a
          href="/register"
          className="text-gray-600 hover:text-blue-400 font-semibold"
        >
          Register
        </a>
      </p>
    </form>
  );
}
