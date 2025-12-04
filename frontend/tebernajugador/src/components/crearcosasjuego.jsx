/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./juego/estilos/EstilosCrear.css"

export const CreacionPantalla = () => {
  const { nombreJuego, nombreUsuario } = useParams();
  const [nuevoNombre, setNuevoNombre] = useState("");
  const [nuevaHistoria, setNuevaHistoria] = useState("");
  const [nuevaSalud, setNuevaSalud] = useState("");
  const [nuevoAtaqueFisico, setNuevoAtaqueFisico] = useState("");
  const [nuevoAtaqueMagico, setNuevoAtaqueMagico] = useState("");
  const [nuevoMana, setNuevoMana] = useState("");
  const [nuevoNivel, setNuevoNivel] = useState("");

  const [nuevoJuegoId, setNuevoJuegoId] = useState("");
  const [nuevaDescripcion, setNuevaDescripcion]= useState();

  const [nuevoRango, setNuevoRango] = useState("");
  const [nuevoLugar, setNuevoLugar] = useState("");
  const [nuevoHechizo, setNuevoHechizo] = useState("");
  const [nuevaImagen, setNuevoImagen] = useState("");
  const [lugar, setLugar] = useState([]);
  const [criaturas, setCriaturas] = useState([]);
  const [hechizos, setHechizo] = useState([]);
  const [loading, setLoading] = useState(true);
  const [CreardorLugar, setCreardorLugar] = useState(false);
  const [CreardorCriatura, setCreardorCriatura] = useState(false);
  const [CreardorHechizo, setCreardorHechizo] = useState(false);

  const crearLugar = async () => {
    try {
      const respuesta = await fetch("http://localhost:8080/lugar/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre: nuevoNombre,
          historia: nuevaHistoria,
          descripcion: nuevaDescripcion,
          juegoId: nuevoJuegoId,
        })
      });
      console.log(nuevoJuegoId);

      if (!respuesta.ok) throw new Error("Error al crear Lugar");

      const nuevaLugar = await respuesta.json();

      alert("Ficha creada correctamente");

      setLugar(nuevaLugar);
    } catch (error) {
      alert("Error: " + error.message);
      console.log(error);
    }
  };

  const crearCriatura = async () => {
    try {
      console.log(nuevoLugar)
      console.log(nuevoHechizo)
      const respuesta = await fetch("http://localhost:8080/criaturas/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre: nuevoNombre,
          historia: nuevaHistoria,
          descripcion: nuevaDescripcion,
          imagen: nuevaImagen,
          salud: nuevaSalud,
          ataque_fisico: nuevoAtaqueFisico,
          ataque_magico: nuevoAtaqueMagico,
          mana: nuevoMana,
          nivel: nuevoNivel,
          rango:nuevoRango,
          lugars: [nuevoLugar],
          hechizos: [nuevoHechizo],
          idJuego: nuevoJuegoId,
        })
      });
      console.log(nuevoJuegoId);

      if (!respuesta.ok) throw new Error("Error al crear Criatura");

      const nuevaCriatura = await respuesta.json();

      alert("Ficha creada correctamente");

      setCriaturas(nuevaCriatura);
    } catch (error) {
      alert("Error: " + error.message);
    }
  };

  const crearHechizo = async () => {
    try {
      console.log(nuevoJuegoId);
      const respuesta = await fetch("http://localhost:8080/hechizo/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre: nuevoNombre,
          historia: nuevaHistoria,
          descripcion: nuevaDescripcion,
          juegoId: nuevoJuegoId,
        })
      });
      

      if (!respuesta.ok) throw new Error("Error al crear Lugar");

      const nuevaLugar = await respuesta.json();

      alert("Ficha creada correctamente");

      setLugar(nuevaLugar);
    } catch (error) {
      console.log("Error: " + error.message);
    }
  };

  useEffect(() => {
    fetch("http://localhost:8080/lugar/lugares")
      .then(res => res.json())
      .then(data => {
        console.log("Lugares recibidos:", data);
        setLugar(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Error al obtener lugares:", err);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/criaturas/listar")
      .then(res => res.json())
      .then(data => {
        console.log("criaturas recibidos:", data);
        setCriaturas(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Error al obtener criaturass:", err);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/hechizo/listar")
      .then(res => res.json())
      .then(data => {
        console.log("criaturas recibidos:", data);
        setHechizo(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Error al obtener criaturass:", err);
        setLoading(false);
      });
  }, []);

 const borrarCriatura =(idCriatura)=> {
        fetch(`http://localhost:8080/criaturas/borrar/${idCriatura}`, {
            method: "DELETE"
        })
            .then(() => {
                // refrescar lista quitando el usuario borrado
                setCriaturas(prev => prev.filter(c => c.id !== criaturas.id));
            })
            .catch(err => console.error("Error al borrar criaturas", err));
      
    }

   const borrarLugar = (idLugar) => {
        fetch(`http://localhost:8080/lugar/borrar/${idLugar}`, {
            method: "DELETE"
        })
            .then(() => {
                // refrescar lista quitando el usuario borrado
                setCriaturas(prev => prev.filter(l => l.id !== lugar.id));
            })
            .catch(err => console.error("Error al borrar usuario", err));
      }

  return (
    <div id="contenedorJuego">

      <div id="pergamino2">
        <div id="lugar">
          <h2>Lugares</h2><button onClick={()=>{setCreardorHechizo(false), setCreardorCriatura(false),setCreardorLugar(true)}} style={{cursor:"pointer"}}>Crear</button>
          <div id="scroll">
            {loading && <p>Cargando lugares...</p>}
            {!loading && (
              <ul>
                {lugar.map((lugar, index) => (
                  <li key={index}>
                    <h2>{lugar.nombre} Id:{lugar.id}
                    </h2>
                    <p>-{lugar.descripcion}</p>
                    <p>-{lugar.historia}</p>
                    <h4>Criaturas:</h4>
                    <ul>
                      {lugar.criatura && lugar.criatura.map((c, i) => (
                        <li key={i}>
                          <p>{c.nombre}</p>
                          <p>-{c.descripcion}</p>
                          (Nivel:{c.nivel})
                        </li>
                      ))}
                    </ul>
                    <button onClick={() => borrarLugar(lugar.id)} style={{ cursor: "pointer" }}>Borrar</button>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>

      <div id="pergamino2">
        <div id="criatura">
          <h2>Criatura</h2><button onClick={()=>{setCreardorHechizo(false), setCreardorCriatura(true),setCreardorLugar(false)}} style={{cursor:"pointer"}}>Crear</button>
          <div id="scroll">
            {loading && <p>Cargando lugares...</p>}
            {!loading && criaturas && (
              <ul>
                {criaturas.map((criaturas, index) => (
                  <li key={index}>
                    <h2>Nombre:{criaturas.nombre} Id{criaturas.id}</h2>
                    <img
                      src={criaturas.imagen}

                      alt={criaturas.nombre}
                      style={{ width: "100px", height: "100px", objectFit: "cover" }}
                    />
                    <p>Descripcion:{criaturas.descripcion}</p>
                    (Nivel:{criaturas.nivel}-Rango:{criaturas.rango})
                    <p>Ataque_Fisico:{criaturas.ataque_fisico}</p>
                    <p>Ataque_magico:{criaturas.ataque_magico}</p>
                    <p>Salud:{criaturas.salud}</p>
                    <p>Mana:{criaturas.mana}</p>
                    <p>Nivel{criaturas.nivel}</p>
                    <br />
                    <button onClick={() => borrarCriatura(criaturas.id)} style={{ cursor: "pointer" }}>Borrar</button>
                    <br/>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>

     {/* <div id="pergamino2">
        <div id="hechizo">
          <h2>Hechizo</h2><button onClick={()=>{setCreardorHechizo(true), setCreardorCriatura(false),setCreardorLugar(false)}} style={{cursor:"pointer"}}>Crear</button>
          <div id="scroll">
            {loading && <p>Cargando ficha...</p>}
            {!loading && hechizos && (
              <ul>
                {hechizos.map((hechizos, index) => (
                  <li key={index}>
                    <h2>Nombre:{hechizos.nombre}</h2>
                    <p>Descripcion:{hechizos.descripcion}</p>
                    (Elemento:{hechizos.elemento}-Tipo:{hechizos.tipo})
                    <p>Ataque_Fisico:{hechizos.ataque_fisico}</p>
                    <p>Ataque_magico:{hechizos.ataque_magico}</p>
                    <p>Salud:{hechizos.salud}</p>
                    <p>Mana:{hechizos.mana}</p>
                    <br />
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>*/}

      {CreardorLugar && (
        <div id="pergamino2">
        <div id="hechizo">
          <h3>Crear nuevo Lugar</h3>          
              <form onSubmit={(e) => { e.preventDefault(); crearLugar(); }}>
                <label>
                  Nombre:
                  <input type="text" value={nuevoNombre} onChange={(e) => setNuevoNombre(e.target.value)} />
                </label>
                <label>
                  Historia:
                  <textarea value={nuevaHistoria} onChange={(e) => setNuevaHistoria(e.target.value)} />
                </label>
                <label>
                  Descripcion:
                  <textarea value={nuevaDescripcion} onChange={(e) => setNuevaDescripcion(e.target.value)} />
                </label> 
                <label>
                  JuegoId:
                  <input type="text" value={nuevoJuegoId} onChange={(e) => setNuevoJuegoId(Number(e.target.value))} />
                </label>
                
                <button type="submit">Crear ficha</button>
              </form>
            </div>
      </div>
      )}

      {CreardorCriatura && (
        <div id="pergamino2">
        <div id="hechizo">
         <h3>Crear nuevo Criatura</h3> 
         <form onSubmit={(e) => { e.preventDefault(); crearCriatura(); }}>
                <label>
                  Nombre:
                  <input type="text" value={nuevoNombre} onChange={e => setNuevoNombre(e.target.value)} />
                </label>
                <label>
                  Imagen:
                  <input type="text" value={nuevaImagen} onChange={e => setNuevoImagen(e.target.value)} />
                </label>
                <label>
                  Historia:
                  <textarea value={nuevaHistoria} onChange={e => setNuevaHistoria(e.target.value)} />
                </label>
                <label>
                  Descripcion:
                  <textarea value={nuevaDescripcion} onChange={e => setNuevaDescripcion(e.target.value)} />
                </label> 
                <label>
                  JuegoId:
                  <input type="text" value={nuevoJuegoId} onChange={e => setNuevoJuegoId(Number(e.target.value))} />
                </label>
                <label>
                  Lugar:
                  <input type="text" value={nuevoLugar} onChange={e => setNuevoLugar(Number(e.target.value))} />
                </label>
                <label>
                  Hechizo:
                  <input type="number" value={nuevoHechizo} onChange={e => setNuevoHechizo(Number(e.target.value))} />
                </label>
                 <label>
                  Salud:
                  <input type="number" value={nuevaSalud} onChange={e => setNuevaSalud(Number(e.target.value))} />
                </label>
                <label>
                  Ataque físico:
                  <input type="number" value={nuevoAtaqueFisico} onChange={e => setNuevoAtaqueFisico(Number(e.target.value))} />
                </label>
                <label>
                  Ataque mágico:
                  <input type="number" value={nuevoAtaqueMagico} onChange={e => setNuevoAtaqueMagico(Number(e.target.value))} />
                </label>
                <label>
                  Mana:
                  <input type="number" value={nuevoMana} onChange={e => setNuevoMana(Number(e.target.value))} />
                </label>
                <label>
                  Nivel:
                  <input type="number" value={nuevoNivel} onChange={e => setNuevoNivel(Number(e.target.value))} />
                </label>
                <label>
                  Rango:
                  <input type="number" value={nuevoRango} onChange={e => setNuevoRango(Number(e.target.value))} />
                </label>
                
                <button type="submit">Crear ficha</button>
              </form>
        </div>
      </div>
      )}


      {CreardorHechizo && (
        <div id="pergamino2">
        <div id="hechizo">
          <h3>Crear nuevo Hechizo</h3> 
         <form onSubmit={crearHechizo}>
                <label>
                  Nombre:
                  <input type="text" value={nuevoNombre} onChange={e => setNuevoNombre(e.target.value)} />
                </label>
                <label>
                  Historia:
                  <textarea value={nuevaHistoria} onChange={e => setNuevaHistoria(e.target.value)} />
                </label>
                <label>
                  Descripcion:
                  <textarea value={nuevaDescripcion} onChange={e => setNuevaDescripcion(e.target.value)} />
                </label> 
                <label>
                  JuegoId:
                  <input type="text" value={nuevoJuegoId} onChange={e => setNuevoJuegoId(Number(e.target.value))} />
                </label>
                
                <button type="submit">Crear ficha</button>
              </form>
        </div>
      </div>
      )}



    </div>



  );

};
export default CreacionPantalla;
