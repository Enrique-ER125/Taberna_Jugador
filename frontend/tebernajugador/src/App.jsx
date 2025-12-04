import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { PuertaApp } from './components/PuertaApp';
import { UsuarioPantalla } from './components/pantalla';
import { JugadoresProvider} from './components/juego/jugadorescontext';
import { JuegoPantalla } from './components/juego/juego';
import {CreacionPantalla} from './components/crearcosasjuego';
import './App.css'

function App() {
    return (

                <JugadoresProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<PuertaApp />} />
                    <Route path="/usuario/:nombre" element={<UsuarioPantalla />} />
                    <Route path="/juego/:nombreGrupo/:nombreJuego/:nombreUsuario" element={<JuegoPantalla />} />
                    //<Route path="/juego/:nombreUsuario" element={< CreacionPantalla/>} />
                </Routes>
            </BrowserRouter>
        </JugadoresProvider>
    );
}
export default App;


