import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "./estilosIn/Mis.css"


export const UsuarioPantalla = () => {

    const { nombre } = useParams();
    const [insigniAbierta, setMostrarInsignia] = useState(false);
    const [libroAbierto, setLibroAbierto] = useState(false);
    const [botella, setBotlla] = useState(false);
    const [grupoSeleccionado, setGrupoSeleccionado] = useState(null);
    const navigate = useNavigate();
    const [juegos, setJuegos] = useState([]);
    const [nombreGrupo, setNombreGrupo] = useState("");
    const [juegoGrupo, setJuegoGrupo] = useState("");
    const [usuarioGrupo, setUsuarioGrupo] = useState("");
    const [grupoInfo, setgrupoInfo] = useState(null);
    const [botellEditando, setBotellEditando] = useState(false);
    const [formGrupoData, setFormGrupoData] = useState({
        nombre: grupoInfo?.nombre || "",
        usuarios: grupoInfo?.usuarios?.map(u => u.id) || [],
        juegos: grupoInfo?.juegos?.map(j => j.id) || [],
    });

    const seleccionarGrupo = (nombreGrupo) => {
        setGrupoSeleccionado(nombreGrupo);
        fetch(`http://localhost:8080/grupo/buscarJuegos?nombreGrupo=${nombreGrupo}`)
            .then(res => res.json())
            .then(data => setJuegos(data))
            .catch(err => console.error("Error al cargar juegos:", err));
    };
    // eslint-disable-next-line no-unused-vars
    const [juegoSeleccionado, setjuegoSeleccionado] = useState(null);

    const [juegoElegido, setJuegoElegido] = useState([]);
    const seleccionarJuego = (nombreJuego) => {
        setjuegoSeleccionado(nombreJuego);
        fetch(`http://localhost:8080/juegos/buscarNombre?nombreJuego=${nombreJuego}`)
            .then(res => res.json())
            .then(data => setJuegoElegido(data))
            .catch(err => console.log("Error al cargar el juego:", err));
    }

    const crearGrupo = async () => {
        try {
            const respuesta = await fetch("http://localhost:8080/grupo/crear", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    nombre: nombreGrupo,
                    juegos: [juegoGrupo],
                    usuarios: [usuarioGrupo]
                })
            });

            if (!respuesta.ok) throw new Error("Error al crear grupo");

            const nuevoGrupo = await respuesta.json();
            alert("Grupo creado correctamente");
            setGrupos([...grupos, nuevoGrupo]);
            setBotlla(false);
        } catch (error) {
            alert("Error: " + error.message);
        }
    };


    const editarGrupo = (idGrupo, datos) => {
        fetch(`http://localhost:8080/grupo/actualizar/${idGrupo}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        })
            .then(res => res.json())
            .then(data => {
                console.log("Grupo actualizado:", data);
                setgrupoInfo(data);
                setEditando(false);
                // refrescar lista

                setGrupos(prev =>
                    prev.map(u => (u.id === idGrupo ? data : u))
                );
            }).catch(err => console.error("Error al actualizar grupo:", err));
    };

    useEffect(() => {
        if (botellEditando && nombre === "Admin" && grupoInfo?.nombre) {
            fetch(`http://localhost:8080/grupo/listarByNombre/nombreGrupo=${grupoInfo.nombre}`)
                .then(res => res.json())
                .then(data => {
                    console.log("Respuesta de grupo:", data);
                    if (Array.isArray(data)) {
                       setgrupoInfo(data);
                    }
                })
                .catch(error => console.error('Error al obtener grupos', error));
        }
    }, [botellEditando, nombre, grupoInfo?.nombre]);

    useEffect(() => {
        if (nombre === "Admin") {
            fetch(`http://localhost:8080/grupo/listar`)
                .then(res => res.json())
                .then(data => {
                    console.log("Respuesta de grupos:", data);
                    if (Array.isArray(data)) {
                        setGrupos(data);
                    }
                })
                .catch(error => console.error('Error al obtener grupos', error));
        }

    }, [nombre]);



    const borrarGrupo = (idUsuario) => {
        fetch(`http://localhost:8080/usuario/borrar/${idUsuario}`, {
            method: "DELETE"
        })
            .then(() => {
                // refrescar lista quitando el usuario borrado
                setUsuario(prev => prev.filter(u => u.id !== idUsuario));
            })
            .catch(err => console.error("Error al borrar usuario", err));
    };

    const [grupos, setGrupos] = useState([]); //para seleccionar los grupos de los usuarios
    useEffect(() => {
        fetch(`http://localhost:8080/usuario/buscarGrupos?nombreUsuario=${nombre}`)
            .then(res => res.json())
            .then(data => {
                console.log("Respuesta de grupos:", data);
                if (Array.isArray(data)) {
                    setGrupos(data);
                }
            })
            .catch(error => console.error('Error al obtener grupos', error));

    }, [nombre]);

    const iniciarJuego = (nombreGrupo, nombreJuego, nombre) => {
        navigate(`/juego/${nombreGrupo}/${nombreJuego}/${nombre}`);
    };

     const crearparaJuego = ( nombre) => {
        navigate(`/juego/${nombre}`);
    };

    const [usuarioInfo, setUsuarioInfo] = useState(null);

    useEffect(() => {
        if (insigniAbierta) {
            fetch(`http://localhost:8080/usuario/buscarBynombre?nombreUsuario=${nombre}`)
                .then(res => res.json())
                .then(data => {
                    if (Array.isArray(data) && data.length > 0) {
                        setUsuarioInfo(data[0]);
                    }
                })
                .catch(err => console.error("Error al cargar usuario:", err));
        }
    }, [insigniAbierta, nombre]);

    const [usuario, setUsuario] = useState([]);
    useEffect(() => {
        if (libroAbierto ) {
            fetch(`http://localhost:8080/usuario/listar`)
                .then(res => res.json())
                .then(data => {
                    console.log("Respuesta de usuario:", data);
                    if (Array.isArray(data)) {
                        setUsuario(data);
                    }
                })
                .catch(error => console.error('Error al obtener usuarios', error));
        }

    }, [libroAbierto, nombre]);

    const actualizarUsuario = (idUsuario, datos) => {
        fetch(`http://localhost:8080/usuario/update/${idUsuario}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        })
            .then(res => res.json())
            .then(data => {
                console.log("Usuario actualizado:", data);
                setUsuarioInfo(data);
                setEditando(false);
                // refrescar lista

                setUsuario(prev =>
                    prev.map(u => (u.id === idUsuario ? data : u))
                );
            });
    };

    const [editando, setEditando] = useState(false);

    const [formData, setFormData] = useState({
        nombre: usuarioInfo?.nombre || "",
        descripcion: usuarioInfo?.descripcion || "",
        contrasena: usuarioInfo?.contrasena || "",
        imagen: usuarioInfo?.imagen || "",
        grupos: usuarioInfo?.grupos || [],
        juegos: usuarioInfo?.juegos || [],
        fichaPersonajes: usuarioInfo?.fichaPersonajes || []
    });

    const borrarUsuario = (idUsuario) => {
        fetch(`http://localhost:8080/usuario/borrar/${idUsuario}`, {
            method: "DELETE"
        })
            .then(() => {
                // refrescar lista quitando el usuario borrado
                setUsuario(prev => prev.filter(u => u.id !== idUsuario));
            })
            .catch(err => console.error("Error al borrar usuario", err));
    };
    
    return (
        <>

            {botellEditando && grupoInfo && (
                <div id="botella">
                    <svg width="1000" height="500">
                        <polygon points="550,45 550,165 750,165 750,45" fill="black" strokeWidth="3"></polygon>
                        <polygon points="452,430 452,145 850,145 850,430" fill="black" strokeWidth="3"></polygon>
                        <polygon points="452,350 452,205 850,205 850,350" fill="green" strokeWidth="3" />

                        <text x="650" y="70" textAnchor="middle" fill="white" fontSize="10"
                            onClick={() => editarGrupo(grupoInfo.id, formGrupoData)} style={{ cursor: "pointer" }}>
                            Editar grupo
                        </text>
                        <text x="650" y="400" fill="white" fontSize="14"
                            onClick={() => setBotellEditando(false)} style={{ cursor: "pointer" }}>
                            cerrar
                        </text>
                        <text x="500" y="250">Nombre: {grupoInfo.nombre}</text>
                        <text x="500" y="280">Juego: {(grupoInfo.juegos ?? []).map(j => j.nombre).join(", ")}</text>
                        <text x="500" y="310">Usuario: {(grupoInfo.usuarios ?? []).map(j => j.nombre).join(", ")}</text>
                    </svg>

                    <input
                        type="text"
                        placeholder="Nombre del grupo"
                        value={formGrupoData.nombre}
                        onChange={(e) => setFormGrupoData({ ...formGrupoData, nombre: e.target.value })}
                        style={{ position: "absolute", top: "240px", left: "62%", width: "150px" }}
                    />
                    <input
                        type="text"
                        placeholder="Juego"
                        value={formGrupoData.juegos.join(",")}
                        onChange={e => setFormGrupoData({ ...formGrupoData, juegos: e.target.value.split(",").map(Number) })}
                        style={{ position: "absolute", top: "270px", left: "62%", width: "150px" }}
                    />
                 
                    <input
                        type="text"
                        placeholder="Usuario"
                        value={formGrupoData.usuarios.join(",")}
                        onChange={(e) => setFormGrupoData({ ...formGrupoData, usuarios: e.target.value.split(",").map(Number) })}
                        style={{ position: "absolute", top: "300px", left: "62%", width: "150px" }}
                    />
                </div>
            )
            }

            {botella && (
                <div id="botella">
                    <svg width="1000" height="500">
                        <polygon points="510,45 510,165 630,165 630,45" fill="black" strokeWidth="3"></polygon>
                        <polygon points="452,430 452,145 700,145 700,430" fill="black" strokeWidth="3"></polygon>
                        <polygon points="452,350 452,205 700,205 700,350" fill="green" strokeWidth="3" />

                        <text x="570" y="70" textAnchor="middle" fill="white" fontSize="10"
                            onClick={() => crearGrupo()}>
                            + Crear grupo
                        </text>
                        <text x="560" y="400" fill="white" fontSize="14"
                            onClick={() => setBotlla(false)}>
                            cerrar
                        </text>
                    </svg>

                    <input
                        type="text"
                        placeholder="Nombre del grupo"
                        value={nombreGrupo}
                        onChange={(e) => setNombreGrupo(e.target.value)}
                        style={{ position: "absolute", top: "240px", left: "50%", width: "150px" }}
                    />
                    <input
                        type="text"
                        placeholder="Juego"
                        value={juegoGrupo}
                        onChange={(e) => setJuegoGrupo(e.target.value)}
                        style={{ position: "absolute", top: "270px", left: "50%", width: "150px" }}
                    />
                    <input
                        type="text"
                        placeholder="Usuario"
                        value={usuarioGrupo}
                        onChange={(e) => setUsuarioGrupo(e.target.value)}
                        style={{ position: "absolute", top: "300px", left: "50%", width: "150px" }}
                    />
                </div>
            )
            }

            {libroAbierto && nombre === "Admin" && (
                <div id="libroAbierto">
                    <svg width="1500" height="500" style={{ backgroundImage: "url('/imagenes/barril.jpg')" }}>
                        <polygon points="100,450 100,45 800,45 800,450" fill="rgb(235, 171, 9)" strokeWidth="3" />
                        <polygon points="110,440 110,55 790,55 790,440" fill="brown" strokeWidth="3" />
                        <polygon points="120,430 120,65 450,65 450,430" fill="white" strokeWidth="3"></polygon>
                        <polygon points="452,430 452,65 780,65 780,430" fill="white" strokeWidth="3"></polygon>
                        <text x="400" y="490" fill="white" fontSize="50" onClick={() => setLibroAbierto(false)} style={{ cursor: "pointer" }}>cerrar</text>

                        {/*Esto <foreignObject>  renderizar HTML dentro del área del SVG, y así sí funcionan los botones. */}
                        <foreignObject x="100" y="80" width="350" height="350">
                            <div xmlns="http://www.w3.org/1999/xhtml"
                                style={{
                                    width: "100%",
                                    height: "100%",
                                    overflowY: "auto",
                                    backgroundColor: "rgba(255,255,255,0.1)"
                                }}>
                                <h3>Lista de usuarios</h3>
                                <ul style={{ padding: "0 10px" }}>
                                    {usuario.map((u, index) => (
                                        <li key={index} style={{ marginBottom: "10px" }}>
                                            <strong>{u.nombre}</strong>
                                            <div>
                                                <img
                                                    src={u.imagen}

                                                    alt={u.nombre}
                                                    style={{ width: "100px", height: "100px", objectFit: "cover" }}
                                                />
                                            </div>
                                            <p>Descripcion: {u.descripcion}</p>
                                            <p>Contraseña: {u.contrasena}</p>
                                            <p>Grupos: {(u.grupos ?? []).map(g => g.nombre).join(", ")}</p>
                                            <p>Juegos: {(u.juegos ?? []).map(j => j.nombre).join(", ")}</p>
                                            <p>Fichas: {(u.fichaPersonajes ?? []).map(f => f.nombre).join(", ")}</p>
                                            <button onClick={() => borrarUsuario(u.id)} style={{ cursor: "pointer" }}>Borrar</button>
                                            <button onClick={() => { setEditando(true); setUsuarioInfo(u); }} style={{ cursor: "pointer" }}>Editar</button>

                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </foreignObject>
                        {editando && usuarioInfo && (
                            <foreignObject x="453" y="80" width="300" height="250">
                                <div xmlns="http://www.w3.org/1999/xhtml" style={{ color: "black" }}>
                                <h2>Editar</h2>
                                    <label>
                                        Imagen:
                                        <input
                                            value={formData.imagen}
                                            onChange={e => setFormData({ ...formData, imagen: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Nombre:
                                        <input
                                            value={formData.nombre}
                                            onChange={e => setFormData({ ...formData, nombre: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Contraseña:
                                        <input
                                            type="password"
                                            value={formData.contrasena}
                                            onChange={e => setFormData({ ...formData, contrasena: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Descripción:
                                        <input
                                            value={formData.descripcion}
                                            onChange={e => setFormData({ ...formData, descripcion: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <br />
                                    <label>
                                        Juegos:
                                        <input
                                            value={formData.juegos}
                                            onChange={e => setFormData({ ...formData, juegos: e.target.value.split(",").map(Number) })}
                                        />
                                    </label>
                                    <br />
                                    <br />
                                    <label>
                                        Grupos:
                                        <input
                                            value={formData.grupos.join(",")}
                                            onChange={e => setFormData({
                                                ...formData, grupos: e.target.value.split(",").map(Number)
                                            })
                                            }
                                        />
                                    </label>
                                    <br />
                                    <br />
                                    <label>
                                        Fichas:
                                        <sinput
                                            value={formData.fichaPersonajes}
                                            onChange={e => setFormData({ ...formData, fichaPersonajes: e.target.value.split(",").map(Number) })}
                                        />
                                    </label>
                                    <br />
                                    <button onClick={() => actualizarUsuario(usuarioInfo.id, formData)} style={{ cursor: "pointer" }}>Guardar</button>
                                    <button onClick={() => setEditando(false)} style={{ cursor: "pointer" }}>Cancelar</button>
                                </div>
                            </foreignObject>
                        )}
                    </svg>
                </div>
            )}

            {libroAbierto && nombre != "Admin" && (
                <div id="libroAbierto">
                    <svg width="1500" height="500" style={{ backgroundImage: "url('/imagenes/barril.jpg')" }}>
                        <polygon points="100,450 100,45 800,45 800,450" fill="rgb(235, 171, 9)" strokeWidth="3" />
                        <polygon points="110,440 110,55 790,55 790,440" fill="brown" strokeWidth="3" />
                        <polygon points="120,430 120,65 450,65 450,430" fill="white" strokeWidth="3"></polygon>
                        <polygon points="452,430 452,65 780,65 780,430" fill="white" strokeWidth="3"></polygon>
                        <text x="400" y="490" fill="white" fontSize="50" onClick={() => setLibroAbierto(false)} style={{ cursor: "pointer" }}>cerrar</text>

                        {/*Esto <foreignObject>  renderizar HTML dentro del área del SVG, y así sí funcionan los botones. */}
                        <foreignObject x="100" y="80" width="350" height="350">
                            <div xmlns="http://www.w3.org/1999/xhtml"
                                style={{
                                    width: "100%",
                                    height: "100%",
                                    overflowY: "auto",
                                    backgroundColor: "rgba(255,255,255,0.1)"
                                }}>
                                <h3>Lista de usuarios</h3>
                                <ul style={{ padding: "0 10px" }}>
                                    {usuario.map((u, index) => (
                                        <li key={index} style={{ marginBottom: "10px" }}>
                                            <strong>{u.nombre}</strong>
                                            <div>
                                                <img
                                                    src={u.imagen}

                                                    alt={u.nombre}
                                                    style={{ width: "100px", height: "100px", objectFit: "cover" }}
                                                />
                                            </div>
                                            <p>Descripcion: {u.descripcion}</p>
                                            <p>Grupos: {(u.grupos ?? []).map(g => g.nombre).join(", ")}</p>
                                            <p>Juegos: {(u.juegos ?? []).map(j => j.nombre).join(", ")}</p>
                                            <p>Fichas: {(u.fichaPersonajes ?? []).map(f => f.nombre).join(", ")}</p>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </foreignObject>
                    </svg>
                </div>
            )}

            {insigniAbierta && usuarioInfo && (
                <div id="insigniAbierta">
                    <svg width="1500" height="500" style={{ backgroundImage: "url('/imagenes/barril.jpg')" }}>
                        <polygon points="400,150 600,25 800,150 800,350 600,450 400,350" fill="rgb(235, 171, 9)" strokeWidth="3" />
                        <polygon points="410,155 600,35 790,155 790,345 600,440 410,345" fill="brown" strokeWidth="3" />
                        <foreignObject x="550" y="160" width="350" height="250"> <img
                            src={usuarioInfo.imagen}

                            alt={usuarioInfo.nombre}
                            style={{ width: "100px", height: "100px", objectFit: "cover" }}
                        /></foreignObject>
                        <text x="550" y="100" fill="white" fontSize="14">Id: {usuarioInfo.id}</text>
                        <text x="470" y="150" fill="white" fontSize="14">Nombre: {usuarioInfo.nombre}</text>
                        <text x="470" y="180" fill="white" fontSize="14">Contraseña: {usuarioInfo.contrasena}</text>
                        <text x="470" y="210" fill="white" fontSize="14">Descripción: {usuarioInfo.descripcion}</text>

                        <text x="470" y="240" fill="white" fontSize="14">
                            Juegos: {(usuarioInfo?.juegos ?? []).map(j => j.nombre).join(", ")}
                        </text>

                        <text x="470" y="270" fill="white" fontSize="14">
                            Grupos: {(usuarioInfo?.grupos ?? []).map(g => g.nombre).join(", ")}
                        </text>

                        <text x="470" y="300" fill="white" fontSize="14">
                            Fichas: {(usuarioInfo?.fichaPersonajes ?? []).map(f => f.nombre).join(", ")}
                        </text>

                        <text x="580" y="350" fill="white" fontSize="14"
                            onClick={() => setEditando(true)} style={{ cursor: "pointer" }}>
                            Editar
                        </text>

                        <text x="580" y="400" fill="white" fontSize="14"
                            onClick={() => setMostrarInsignia(false)} style={{ cursor: "pointer" }}>
                            cerrar
                        </text>*
                        {editando && (
                            <foreignObject x="550" y="140" width="300" height="200">
                                <div xmlns="http://www.w3.org/1999/xhtml" style={{ color: "black" }}>
                                    <label>
                                        Imagen:
                                        <input
                                            value={formData.imagen}
                                            onChange={e => setFormData({ ...formData, imagen: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Nombre:
                                        <input
                                            value={formData.nombre}
                                            onChange={e => setFormData({ ...formData, nombre: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Contraseña:
                                        <input
                                            type="password"
                                            value={formData.contrasena}
                                            onChange={e => setFormData({ ...formData, contrasena: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Descripción:
                                        <input
                                            value={formData.descripcion}
                                            onChange={e => setFormData({ ...formData, descripcion: e.target.value })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Juegos:
                                        <input
                                            value={formData.juegos.join(",")}
                                            onChange={e => setFormData({ ...formData, juegos: e.target.value.split(",").map(Number) })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Grupos:
                                        <input
                                            multiple
                                            value={formData.grupos.join(",")}
                                            onChange={e => setFormData({ ...formData, grupos: e.target.value.split(",").map(Number) })}
                                        />
                                    </label>
                                    <br />
                                    <label>
                                        Fichas:
                                        <input
                                            value={formData.fichaPersonajes.join(",")}
                                            onChange={e => setFormData({ ...formData, fichaPersonajes: e.target.value.split(",").map(Number) })}
                                        />
                                    </label>
                                    <br />
                                    <button onClick={() => actualizarUsuario(usuarioInfo.id, formData)} style={{cursor:"pointer"}}>Guardar</button>
                                    <button onClick={() => setEditando(false)} style={{cursor:"pointer"}}>Cancelar</button>
                                </div>
                            </foreignObject>

                        )}
                    </svg>
                </div>

            )}

            <div id="contenedor2">
                <div id="cabecera">
                    <div id="imgCabecera">
                        <div id="letrCabe">Taberna</div><img src="/imagenes/icono-removebg-preview.png" />
                        <div id="letrCabe"> del jugador</div>
                    </div>
                </div>
                <div id="postbarra">
                    <div id="primerBarril">
                        <div id="scroll">
                            {nombre != "Admin" && (
                                <svg width="110" height={50 + (grupos.length + 1) * 80} style={{
                                    backgroundImage: "url('/imagenes/barril.jpg')",
                                    border: "4px solid goldenrod"
                                }}>
                                    {Array.isArray(grupos) && grupos.map((grupo, index) => {
                                        const y = 50 + index * 100;
                                        return (
                                            <g key={index}>
                                                <circle cx="55" cy={y} r="35" fill="black" stroke="blue" strokeWidth="1" onClick={() => seleccionarGrupo(grupo.nombre)} style={{ cursor: "pointer" }} />

                                                <line x1="0" y1={y + 50} x2="200" y2={y + 50} stroke="goldenrod" strokeWidth="2"></line>
                                                <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10"
                                                >
                                                    {grupo.nombre}
                                                </text>

                                            </g>
                                        );
                                    })}
                                    <g>
                                        {(() => {
                                            const y = 70 + grupos.length * 80;
                                            return (
                                                <>
                                                    <circle cx="55" cy={y} r="35" fill="darkgreen" stroke="white" strokeWidth="1" onClick={() => setBotlla(true)} style={{ cursor: "pointer" }} />
                                                    <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10">
                                                        + Crear grupo
                                                    </text>
                                                </>
                                            );
                                        })()}
                                    </g>
                                </svg>
                            )}
                            {nombre === "Admin" && (
                                <svg width="160" height={90 + (grupos.length + 1) * 80} style={{
                                    backgroundImage: "url('/imagenes/barril.jpg')",
                                    border: "4px solid goldenrod"
                                }}>
                                    {Array.isArray(grupos) && grupos.map((grupo, index) => {
                                        const y = 50 + index * 100;

                                        return (
                                            <g key={index}>
                                                <circle
                                                    cx="55"
                                                    cy={y}
                                                    r="35"
                                                    fill="black"
                                                    stroke="blue"
                                                    strokeWidth="1"
                                                    onClick={() => seleccionarGrupo(grupo.nombre)}
                                                    style={{ cursor: "pointer" }}
                                                />

                                                <line x1="0" y1={y + 50} x2="200" y2={y + 50} stroke="goldenrod" strokeWidth="2"></line>
                                                <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10">
                                                    {grupo.nombre}
                                                </text>

                                                {/* Botones de editar/borrar al lado */}
                                                <foreignObject x="70" y={y - 10} width="100" height="40">
                                                    <div xmlns="http://www.w3.org/1999/xhtml">
                                                    {/*console.log(grupo)*/}
                                                        <button onClick={() => { setBotellEditando(true); setgrupoInfo(grupo) }} style={{ cursor: "pointer" }}>Editar</button>
                                                        <br />
                                                        <button onClick={() => borrarGrupo(grupo.id)} style={{ cursor: "pointer" }}>Borrar</button>
                                                    </div>
                                                </foreignObject>
                                            </g>
                                        );
                                    })}
                                    <g>
                                        {(() => {
                                            const y = 70 + grupos.length * 95;
                                            return (
                                                <>
                                                    <circle cx="55" cy={y} r="35" fill="darkgreen" stroke="white" strokeWidth="1" onClick={() => setBotlla(true)} style={{ cursor: "pointer" }} />
                                                    <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10">
                                                        + Crear grupo
                                                    </text>
                                                </>
                                            );
                                        })()}
                                    </g>
                                </svg>
                            )}


                        </div>
                    </div>
                    <div id="central">
                        <button onClick={() => setLibroAbierto(true)} style={{cursor:"pointer"}}><img id="libro" src="/imagenes/libro.JPG" /></button>


                        {Array.isArray(juegoElegido) && juegoElegido.map((juego, index) => {
                            const y = 40 + index * 80;
                            return (
                                <g key={index}>
                                    <div id="jugar" onClick={() => iniciarJuego(grupoSeleccionado, juego.nombre, nombre)} style={{ cursor: "pointer" }}>
                                        <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10" >
                                            {juego.nombre}
                                        </text>
                                    </div>
                                </g>
                            );
                        })}
                        <div id="barman"><div id='dialogo'>Bienvenido, {nombre}</div><img src="/imagenes/icono-removebg-preview.png" /></div>
                        <div id="insignia" onClick={() => setMostrarInsignia(true)} style={{ cursor: "pointer" }} >
                            <svg width="50" height="50" viewBox="0 0 60 50">
                                <polygon points="30,5 55,22 45,50 15,50 5,22" fill="brown" strokeWidth="3" />
                                <text x="28" y="30" textAnchor="middle" fill="white" fontSize="10">insignia</text>
                            </svg>
                        </div>
                    </div>
                    <div id="segundoBarril">
                        <div id="scroll">
                            {nombre === "Admin" && (
                            <svg width="110" height={50 + (juegos.length + 1) * 80} style={{
                                backgroundImage: " url('/imagenes/barril.jpg')",
                                border: "4px solid goldenrod"
                            }}>
                                {Array.isArray(juegos) && juegos.map((juego, index) => {
                                    const y = 50 + index * 80;
                                    return (
                                        <g key={index}>
                                            <circle cx="55" cy={y} r="35" fill="darkgreen" stroke="white" strokeWidth="1" onClick={() => seleccionarJuego(juego.nombre)} style={{ cursor: "pointer" }} />
                                            <line x1="0" y1={y + 50} x2="200" y2={y + 50} stroke="goldenrod" strokeWidth="2"></line>
                                            <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10"
                                            >
                                                {juego.nombre}
                                            </text>
                                        </g>
                                    );
                                })}
                                
                                <g>
                                        {(() => {
                                            const y = 70 + juegos.length * 80;
                                            return (
                                                <>
                                                    <circle cx="55" cy={y} r="35" fill="darkgreen" stroke="white" strokeWidth="1" onClick={() => crearparaJuego(nombre)} style={{ cursor: "pointer" }} />
                                                    <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10">
                                                        + Crear juego
                                                    </text>
                                                </>
                                            );
                                        })()}
                                    </g>                                   
                            </svg>
                            )}
                            {nombre != "Admin" && (
                            <svg width="110" height={10 + juegos.length * 80} style={{
                                backgroundImage: " url('/imagenes/barril.jpg')",
                                border: "4px solid goldenrod"
                            }}>
                                {Array.isArray(juegos) && juegos.map((juego, index) => {
                                    const y = 40 + index * 80;
                                    return (
                                        <g key={index}>
                                            <circle cx="55" cy={y} r="35" fill="darkgreen" stroke="white" strokeWidth="1" onClick={() => seleccionarJuego(juego.nombre)} style={{ cursor: "pointer" }} />
                                            <line x1="0" y1="80" x2="200" y2="80" stroke="goldenrod" strokeWidth="2"></line>
                                            <text x="55" y={y + 5} textAnchor="middle" fill="white" fontSize="10"
                                            >
                                                {juego.nombre}
                                            </text>
                                        </g>
                                    );
                                })}                                                                                       
                            </svg>
                            )}
                        </div>
                    </div>
                </div>
                <div id="barra"></div>
            </div>
        </>
    );
};
