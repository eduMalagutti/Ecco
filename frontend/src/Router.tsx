import { Routes, Route } from 'react-router-dom'
//import { App } from './App'
import { Home } from './pages/Home'
import { Login } from './pages/login/Login'
import { Cadastro } from './pages/cadastro/Cadastro'
import { VerificacaoCadastro } from './pages/verificacaoCadastro/VerificacaoCadastro'
import { MudarSenha } from './pages/mudarSenha/MudarSenha'
import { MeuPerfil } from './pages/meuPerfil/MeuPerfil'
import { CadastroDocumento } from './pages/CadastroDocumento'
import { VerificacaoEsqueciSenha } from './pages/VerificacaoEsqueciSenha'
import { EsqueciMinhaSenha } from './pages/EsqueciMinhaSenha'
import { App } from './App'
import { MeusDados } from './pages/MeusDados/MeusDados'
import { Contato } from './pages/Contato/Contato'



export function Router() {
    return (
        <Routes>
            <Route path="/" element={<Home />}/>     
            <Route path="/cadastro" element={<Cadastro />}/>     
            <Route path="/login" element={<Login />}/>     
            <Route path="/meu-perfil" element={<MeuPerfil />}/>     
            <Route path="/mudar-senha" element={<MudarSenha />}/>     
            <Route path="/verificacao-cadastro" element={<VerificacaoCadastro />}/>
            <Route path="/esqueci-senha" element={<EsqueciMinhaSenha />}/>     
            <Route path="/verificacao-esqueci-senha" element={<VerificacaoEsqueciSenha />}/>
            <Route path="/cadastroDocumento" element={<CadastroDocumento />}/>     
            <Route path="/meusdados" element={<MeusDados/>}/>
            <Route path='/contato' element={<Contato/>}/>
            <Route path="/app" element={<App />}/>
        </Routes>
    )
}