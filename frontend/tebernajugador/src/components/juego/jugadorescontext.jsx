import React, { useState,useEffect } from "react";


const JugadoresContext = React.createContext();

export const JugadoresProvider = ({ children,nombreUsuario }) => {
  const [jugadores, setJugadores] = useState([]);

  const addJugador = (nombre) => {
    setJugadores(prev => {
      if (prev.find(j => j.nombre === nombre)) return prev;
      return [...prev, { nombre }];
    });
  };

  useEffect(() => {
  if (nombreUsuario) {
    addJugador(nombreUsuario);
  }
}, [nombreUsuario]);

  return (
    <JugadoresContext.Provider value={{ jugadores, addJugador }}>
      {children}
    </JugadoresContext.Provider>
  );
};

export default JugadoresContext;