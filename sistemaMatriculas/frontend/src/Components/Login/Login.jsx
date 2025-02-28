import { useState } from "react";
import axios from "axios";
import styles from "./Login.module.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        username,
        password,
      });
      localStorage.setItem("token", response.data.token);
      setMessage("Login realizado com sucesso!");
    } catch (error) {
      setMessage("Usuário ou senha incorretos.");
    }
  };

  return (
    <container className={styles.container}>
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <input
          className={styles.input}
          type="text"
          placeholder="Usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          className={styles.input}
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className={styles.button} type="submit">
          Entrar
        </button>
      </form>
      <p>{message}</p>
    </container>
  );
};

export default Login;
