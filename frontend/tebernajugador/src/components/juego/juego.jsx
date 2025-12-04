import React, { useState, useEffect, useContext } from "react";
import { useParams } from "react-router-dom";
import JugadoresContext from "./jugadorescontext";
import "./estilos/Estilos.css"

export const JuegoPantalla = () => {
  const { nombreJuego, nombreUsuario,nombreGrupo } = useParams();
  const [lugar, setLugar] = useState([]);
  const [boss, setBoss] = useState(null);
  const [criaturasPendientes, setCriaturasPendientes] = useState([]);
  const [criaturaActual, setCriaturaActual] = useState(null);
  const [ficha, setFicha] = useState(null);
  const [loading, setLoading] = useState(true);
  const [mazmorraSeleccionada, setMazmorraSeleccionada] = useState(null);
  // eslint-disable-next-line no-unused-vars
  const [grupoSeleccionado, setGrupoSeleccionado] = useState(nombreGrupo);
  const [saludCriatura, setSaludCriatura] = useState(0);
  const [saludJugador, setSaludJugador] = useState(0);
  const [derrota, setDerrota] = useState(false);
  const [victoria, setVictoria] = useState(false);
  const [puntosDisponibles, setPuntosDisponibles] = useState(0);
  const [nuevoNombre, setNuevoNombre] = useState("");
  const [nuevaHistoria, setNuevaHistoria] = useState("");
  const [nuevaSalud, setNuevaSalud] = useState();
  const [nuevoAtaqueFisico, setNuevoAtaqueFisico] = useState();
  const [nuevoAtaqueMagico, setNuevoAtaqueMagico] = useState();
  const [nuevoMana, setNuevoMana] = useState();
  const [nuevoNivel, setNuevoNivel] = useState();
  const [nuevoJuegoId, setNuevoJuegoId] = useState();
  const [nuevoUsuarioId, setNuevoUsuarioId] = useState();
 // const [jugadoresBackend, setJugadoresBackend] = useState([]);

  const { jugadores, addJugador } = useContext(JugadoresContext);
  const [ordenTurnos, setOrdenTurnos] = useState([]);

  useEffect(() => {
    if (nombreUsuario) {
      addJugador(nombreUsuario);
    }
  }, [addJugador, nombreUsuario]);


  const stats = [
    { key: "ataque_fisico", label: "Ataque físico" },
    { key: "ataque_magico", label: "Ataque mágico" },
    { key: "salud", label: "Salud" },
    { key: "mana", label: "Mana" }
  ];

  const [increments, setIncrements] = useState({});

//conestar entre Usuarios para jugar 
useEffect(() => {
    if (nombreUsuario) {
      const jugadoresGuardados = JSON.parse(localStorage.getItem("jugadores")) || [];
      if (!jugadoresGuardados.find(j => j.nombre === nombreUsuario)) {
        jugadoresGuardados.push({ nombre: nombreUsuario });
        localStorage.setItem("jugadores", JSON.stringify(jugadoresGuardados));
      }
      jugadoresGuardados.forEach(j => addJugador(j.nombre));
    }
  }, [nombreUsuario, addJugador]);

  // decidir turnos 
const decidirOrdenTurnos = () => {
  let ordenGuardado = JSON.parse(localStorage.getItem("ordenTurnos"));
  if (!ordenGuardado) {
    const tiradasUsuarios = jugadores.map(jugador => ({
      ...jugador,
      dado: Math.floor(Math.random() * 12) + 1
    }));
    const criatura = { nombre: "Criatura", dado: Math.floor(Math.random() * 12) + 1 };
    ordenGuardado = [tiradasUsuarios[0], criatura, tiradasUsuarios[1]];
    localStorage.setItem("ordenTurnos", JSON.stringify(ordenGuardado));
  }
  setOrdenTurnos(ordenGuardado);
};


  const iniciarMazmorra = (lugar) => {
    console.log("Mazmorra seleccionada:", lugar);
    setMazmorraSeleccionada(lugar);

    const bossCriatura = lugar.criatura.reduce((max, c) =>
      c.nivel > max.nivel ? c : max
    );

    setBoss(bossCriatura);

    const otrasCriaturas = lugar.criatura.filter(c => c.id !== bossCriatura.id);
    const mezcladas = otrasCriaturas.sort(() => Math.random() - 0.5);

    setCriaturasPendientes(mezcladas);
    const primera = mezcladas[0] || bossCriatura;
    setCriaturaActual(primera);
    setSaludCriatura(primera.salud);

    decidirOrdenTurnos();
  };

  const atacarFisico = () => {
    if (!ficha) return;
    const dado = Math.floor(Math.random() * 12) + 1;
    const daño = ficha.ataque_fisico * (dado / 12);
    const nuevaSalud = Math.max(0, saludCriatura - daño);
    setSaludCriatura(nuevaSalud);
    if (nuevaSalud > 0) ataqueCriatura();
    if (nuevaSalud <= 0) {
      console.log("Hola");
      ganarExperiencia(criaturaActual.nivel);
    }
  };

  const atacarMagico = () => {
    if (!ficha) return;
    const dado = Math.floor(Math.random() * 12) + 1;
    const daño = ficha.ataque_magico * (dado / 12);
    const nuevaSalud = Math.max(0, saludCriatura - daño);
    setSaludCriatura(nuevaSalud);
    if (nuevaSalud > 0) ataqueCriatura();
    if (saludCriatura <= 0) {
      ganarExperiencia(criaturaActual.nivel);
    }
  };

  const ataqueCriatura = () => {
    const dado = Math.floor(Math.random() * 12) + 1;
    const tipo = Math.random() < 0.5 ? "fisico" : "magico";

    let daño = 0;
    if (tipo === "fisico") {
      daño = criaturaActual.ataque_fisico * (dado / 12);
    } else {
      daño = criaturaActual.ataque_magico * (dado / 12);
    }
    console.log("Salud antes" + saludJugador);
    const nuevaSaludJugador = Math.max(0, saludJugador - daño);
    console.log("daño" + daño)
    console.log("nauevaSalud" + nuevaSaludJugador);
    setSaludJugador(nuevaSaludJugador);
    console.log("Salud" + saludJugador);
  };


  const ganarExperiencia = (nivelCriatura) => {
    if (!ficha) return;

    let nuevaExperiencia = (ficha.experiencia || 0) + nivelCriatura;
    let nuevoNivel = ficha.nivel;

    let fichaActualizada;

    if (nuevaExperiencia >= ficha.nivel) {
      nuevoNivel += nuevaExperiencia - ficha.nivel;
      setPuntosDisponibles(prev => prev + nuevaExperiencia - ficha.nivel);

      fichaActualizada = {
        ...ficha,
        experiencia: 0,
        nivel: nuevoNivel
      };
    } else {
      fichaActualizada = {
        ...ficha,
        experiencia: nuevaExperiencia,
        nivel: nuevoNivel
      };
    }

    fetch(`http://localhost:8080/ficha/update/${ficha.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(fichaActualizada)
    })
      .then(res => {
        if (!res.ok) throw new Error("Error en la actualización");
        // no hacemos res.json() porque el backend no devuelve nada
        return;
      })
      .then(() => {
        // refrescamos la ficha con un GET
        return fetch(`http://localhost:8080/usuario/buscarFichaByUsuario?nombreUsuario=${nombreUsuario}`);
      })
      .then(res => res.json())
      .then(data => setFicha(data[0]))
      .catch(err => console.error("Error al mejorar stat:", err));

  }

  const handleIncrementChange = (statKey, value) => {
    setIncrements(prev => ({
      ...prev,
      [statKey]: Number(value)
    }));
  };
  //LIMPIAR 
  useEffect(() => { 
    localStorage.removeItem("estadoMazmorra"); // limpia estado previo 
  localStorage.removeItem("ordenTurnos") 
  setVictoria(false); 
   setDerrota(false); }, []);

  const mejorarStat = (statKey) => {
    if (!ficha || ficha.puntosDisponibles <= 0) return;
    const incremento = increments[statKey] || 0;
    if (incremento <= 0) return;
    if (incremento > puntosDisponibles) {
      alert("No tienes suficientes puntos disponibles");
      return;
    }

    const fichaActualizada = { ...ficha };
    fichaActualizada[statKey] += incremento;
    setPuntosDisponibles(prev => prev - incremento);

    fetch(`http://localhost:8080/ficha/update/${ficha.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(fichaActualizada)
    })
      .then(res => {
        if (!res.ok) throw new Error("Error en la actualización");
        // no hacemos res.json() porque el backend no devuelve nada
        return;
      })
      .then(() => {
        // refrescamos la ficha con un GET
        return fetch(`http://localhost:8080/usuario/buscarFichaByUsuario?nombreUsuario=${nombreUsuario}`);
      })
      .then(res => res.json())
      .then(data => setFicha(data[0]))
      .catch(err => console.error("Error al mejorar stat:", err));

  };


  const avanzar = () => {
    if (saludCriatura > 0) return;

    if (criaturasPendientes.length > 0) {
      const siguiente = criaturasPendientes[0];
      setCriaturaActual(siguiente);
      setSaludCriatura(siguiente.salud);
      setCriaturasPendientes(criaturasPendientes.slice(1));
    } else if (boss) {
      setCriaturaActual(boss);
      setSaludCriatura(boss.salud);
    }
  };

  const crearFicha = async () => {
    try {
      const respuesta = await fetch("http://localhost:8080/ficha/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre: nuevoNombre,
          historia: nuevaHistoria,
          salud: nuevaSalud,
          ataque_fisico: nuevoAtaqueFisico,
          ataque_magico: nuevoAtaqueMagico,
          mana: nuevoMana,
          nivel: nuevoNivel,
          juegoId: nuevoJuegoId,
          usuarioId: nuevoUsuarioId
        })
      });

      if (!respuesta.ok) throw new Error("Error al crear ficha");

      const nuevaFicha = await respuesta.json();

      alert("Ficha creada correctamente");

      setFicha(nuevaFicha);
      setSaludJugador(nuevaFicha.salud);
    } catch (error) {
      alert("Error: " + error.message);
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
    if (saludJugador === 0 && ficha) {
      setDerrota(true);
      setTimeout(() => {
        setMazmorraSeleccionada(null); // vuelve a la lista
        setCriaturaActual(null);
        setBoss(null);
        setCriaturasPendientes([]);
        setSaludJugador(ficha.salud || 100); // reinicia salud
        setDerrota(false);
      }, 3000); // espera 3 segundos
    }
  }, [ficha, saludJugador]);

  useEffect(() => {
    if (boss && criaturaActual === boss && saludCriatura === 0) {
      setVictoria(true);
      setTimeout(() => {
        setMazmorraSeleccionada(null); // vuelve a la lista
        setCriaturaActual(null);
        setBoss(null);
        setCriaturasPendientes([]);
        setSaludJugador(ficha.salud || 100); // reinicia salud
        setVictoria(false);
      }, 3000); // espera 3 segundos
    }
  }, [criaturaActual, boss, ficha, saludCriatura]);

  useEffect(() => {
    fetch(`http://localhost:8080/usuario/buscarFichaByUsuario?nombreUsuario=${nombreUsuario}`)
      .then(res => {
        if (!res.ok) throw new Error("Error en la respuesta del servidor");
        return res.json();
      })
      .then(data => {
        console.log("Ficha recibida:", data);

        if (Array.isArray(data) && data.length > 0) {
          const personaje = data[0];
          setFicha(personaje);
          setSaludJugador(personaje.salud);
        } else {
          setFicha(null); // no hay ficha → mostrar formulario de creación
        }
      })
      .catch(err => {
        console.error("Error al obtener ficha:", err);
        setFicha(null);
      });
  }, [nombreUsuario]);


  return (
    <div id="contenedorJuego">
      <div id="vaso">
        <p>Estás jugando: {nombreJuego}</p>
      </div>
      <div id="pergamino">
        <div id="lugar">
          <div id="scroll2">
            {loading && <p>Cargando lugares...</p>}

            {!loading && !mazmorraSeleccionada && (
              <ul>
                {lugar.map((lugar, index) => (
                  <li key={index}>
                    <h2
                      onClick={() => iniciarMazmorra(lugar)}
                      style={{ cursor: "pointer", background: "rgba(219, 156, 9, 1)" }}
                    >
                      {lugar.nombre}
                    </h2>
                    <p>-{lugar.descripcion}</p>
                    <p>-{lugar.historia}</p>
                    <h4>Criaturas:</h4>
                    <ul>
                      {lugar.criatura && lugar.criatura.map((c, i) => (
                        <li key={i}>
                          <p>{c.nombre}</p>
                          <img src={c.imagen} alt={c.nombre} style={{ width: "100px", height: "100px", objectFit: "cover" }}/>
                          <p>-{c.descripcion}</p>
                          (Nivel:{c.nivel})
                        </li>
                      ))}
                    </ul>
                  </li>
                ))}
              </ul>
            )}
            {!loading && mazmorraSeleccionada && criaturaActual && (
              <div>
                <h2>Has entrado en {mazmorraSeleccionada.nombre}</h2>

                <h3>Criatura actual:</h3>
                <div>
                  <p>{criaturaActual.nombre}</p><img src={criaturaActual.imagen} alt={criaturaActual.nombre} style={{ width: "100px", height: "100px", objectFit: "cover" }}/>
                  <p>Salud criatura: {saludCriatura}</p>
                  <p>(Nivel: {criaturaActual.nivel})</p>
                </div>
                {saludCriatura === 0 && (
                  <p style={{ color: "red", fontWeight: "bold" }}>¡Derrotado!</p>
                )}
                {saludCriatura === 0 && (
                  <button onClick={avanzar} style={{ cursor: "pointer", background: "rgb(235, 179, 9)" }}>Avanzar al siguiente combate</button>
                )}
              </div>
            )}
          </div>
        </div>
      </div>

      {ordenTurnos.length > 0 && (
        <div id="pergamino">
          <div id="ficha">
            <h3>Jugadores conectados: {jugadores.length}</h3>
            <ul>
              {Array.isArray(jugadores) &&
                jugadores.map((j, i) => (

                  <li key={i}>{j?.nombre ?? "Sin nombre"}</li>

                ))}
            </ul>
            <h2>Orden de turnos</h2>
            <ul>
              {ordenTurnos.map((j, idx) => (
                <li key={j.nombre}>
                  {idx + 1}. {j.nombre} (tirada: {j.dado})
                </li>
              ))}
            </ul>
          </div>
        </div>
      )}

      <div id="pergamino">
        <div id="ficha">
          <h2>Ficha del Usuario</h2>
          {loading && <p>Cargando ficha...</p>}
          {!loading && !ficha && (
            <div>
              <h3>Crear nueva ficha</h3>
              <form onSubmit={crearFicha}>
                <label>
                  Nombre:
                  <input type="text" value={nuevoNombre} onChange={e => setNuevoNombre(e.target.value)} />
                </label>
                <label>
                  Historia:
                  <textarea value={nuevaHistoria} onChange={e => setNuevaHistoria(e.target.value)} />
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
                  JuegoId:
                  <input type="number" value={nuevoJuegoId} onChange={e => setNuevoJuegoId(Number(e.target.value))} />
                </label>
                <label>
                  UsuarioId:
                  <input type="number" value={nuevoUsuarioId} onChange={e => setNuevoUsuarioId(Number(e.target.value))} />
                </label>
                <button type="submit">Crear ficha</button>
              </form>
            </div>
          )}
          {!loading && ficha && (
            <div>
              <strong>Nombre: {ficha.nombre}</strong>
              <p>Historia: {ficha.historia}</p>
              <p>Salud: {saludJugador}/{ficha.salud}</p>
              <p>Mana: {ficha.mana}</p>
              <p>
                Ataque_Fisico: {ficha.ataque_fisico}
                <button id="atacar" onClick={atacarFisico} style={{ cursor: "pointer" }}>Atacar</button>
              </p>
              <p>
                Ataque_magico: {ficha.ataque_magico}
                <button id="atacar" onClick={atacarMagico} style={{ cursor: "pointer" }}>Atacar</button>
              </p>
              <p>Nivel: {ficha.nivel}</p>
              <p>Experiencia: {ficha.experiencia}</p>
            </div>
          )}
          {puntosDisponibles > 0 && (
            <>
              <p>Puntos disponibles: {puntosDisponibles}</p>
              {stats.map(stat => (
                <div key={stat.key}>
                  <label>{stat.label}</label>
                  <input
                    type="number"
                    min="1"
                    value={increments[stat.key] || ""}
                    onChange={(e) => handleIncrementChange(stat.key, e.target.value)}
                  />
                  <button onClick={() => mejorarStat(stat.key)}>
                    + Mejorar {stat.label}
                  </button>
                </div>
              ))}
            </>
          )}

        </div>
      </div>
      {derrota && (
        <div className="overlay">
          Has perdido
        </div>
      )}
      {victoria && (
        <div className="overlay">
          Has ganado al boos
          Felicidades
        </div>
      )}
    </div>

  );

};
export default JuegoPantalla;
