import { Routes, Route } from 'react-router-dom'
//import { App } from './App'
import { Home } from './pages/Home/Home.tsx'
import { Login } from './pages/Login/Login.tsx'
import { Cadastro } from './pages/Cadastro/Cadastro.tsx'
import { VerificacaoCadastro } from './pages/VerificacaoCadastro/VerificacaoCadastro.tsx'
import { MudarSenha } from './pages/MudarSenha/MudarSenha.tsx'
import { MeuPerfil } from './pages/MeuPerfil/MeuPerfil.tsx'
import { CadastroDocumento } from './pages/CadastroDocumento/CadastroDocumento.tsx'
import { VerificacaoEsqueciSenha } from './pages/VerificacaoEsqueciSenha/VerificacaoEsqueciSenha.tsx'
import { EsqueciMinhaSenha } from './pages/EsqueciMinhaSenha/EsqueciMinhaSenha.tsx'
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