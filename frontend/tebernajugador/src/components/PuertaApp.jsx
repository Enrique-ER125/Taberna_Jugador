import { useState } from 'react';
import { Await, useNavigate } from 'react-router-dom';
import './puerta.css';

export const PuertaApp = () => {
    const [usuario, setUsuario] = useState('');
    const [contrasena, setContrasena] = useState('');
    const [nuevoUsuario, setNuevoUsuario] = useState('');
    const [nuevaContrasena, setNuevaContrasena] = useState('');
    const [puertasAbiertas, setPuertasAbiertas] = useState(false);
    const navigate = useNavigate();
   
const inicio = async () => {
    try {
        const respuesta = await fetch('http://localhost:8080/usuario/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nombre: usuario, contrasena })
        });

        if (!respuesta.ok) throw new Error('Credenciales incorrectas');

        const datos = await respuesta.json(); 

        setPuertasAbiertas(true);
        setTimeout(() => {
      navigate(`/usuario/${usuario}`, { state: datos });
       }, 2000);
    } catch (error) {
        alert('Error al iniciar sesión: ' + error.message);
    }
};

const registrar = async () => {
    try {
      const respuesta = await fetch('http://localhost:8080/usuario/crear', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nombre: nuevoUsuario, contrasena: nuevaContrasena })
      });

      if (!respuesta.ok) throw new Error('Error al registrar usuario');

      setPuertasAbiertas(true);

    setTimeout(() => {
      navigate(`/usuario/${nuevoUsuario}`);
    }, 2000);
    } catch (error) {
      alert('Error al registrarse: ' + error.message);
    }
  };

    return (
        <>
                    <div id="contenedor">
                        <div id="puertaIzq" className={puertasAbiertas ? "abierta" : ""}>
                            <h1>Taberna Del</h1>
                            <div id="Iniciar">
                                <h2>Iniciar Sesion</h2>
                                <label>Usuario</label>
                                <input type="text" name="Usuario" 
                                value={usuario} onChange={(e)=> setUsuario(e.target.value)}/>
                                <label>Contraseña</label>
                                <input type="password" name="Contraseña" 
                                value={contrasena} onChange={(e)=> setContrasena(e.target.value)}/>
                                <button onClick={inicio}>Entrar</button>
                                <a href="http://localhost:5173/usuario/Invitado"> 
                                    <div id="Invitado">
                                        <h2>Invitado</h2>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div id="puertaDech" className={puertasAbiertas ? "abierta" : ""}> 
                            <h1>Jugador</h1>
                            <div id="Registrarse">
                            <h2>Registrarse</h2>
                            <label>Usuario</label>
                            <input type="text" value={nuevoUsuario} onChange={(e) => setNuevoUsuario(e.target.value)} />
                            <label>Contraseña</label>
                            <input type="password" value={nuevaContrasena} onChange={(e) => setNuevaContrasena(e.target.value)} />
                            <button onClick={registrar}>Crear cuenta</button>
                        </div></div>
                    </div>
        </>
    )
}