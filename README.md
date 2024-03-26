<h1 align="center">
<br>
Globoplay movie app
</h1>

# 📋 Índice

- [Telas](#-Telas)
- [Sobre o projeto](#-Sobre-o-projeto)
- [Tecnologias utilizadas](#-Tecnologias-utilizadas)
- [Rodando o app](#-Rodando-o-app)

## 🎨 Telas

<p float="left">
<img src="https://github.com/globoi/globoplay-desafio-mobile/blob/master/assets/screenshots/home.jpg" width-="400" height="400">
<img src="https://github.com/globoi/globoplay-desafio-mobile/blob/master/assets/screenshots/my-list.jpg" width-="400" height="400">
<img src="https://github.com/globoi/globoplay-desafio-mobile/blob/master/assets/screenshots/highlights.jpg" width-="400" height="400">
</p>

## 📃 Sobre o projeto

<p align="center">
A ideia do app veio através da lista de desafios do repositório entitulado como <a href="https://github.com/robsonsilv4/mobile-challenges?tab=readme-ov-file">mobile challenge</a>, após consultar a lista de desafios escolhi fazer o da globoplay como proposto no repo.
</p>
<p>
A proposta do projeto é construir um app em Android baseado nas telas diposnibilizadas no repo, os dados do app são provenientes da API pública do TMDB.
</p>

## 🛠 Tecnologias utilizadas

- 𝗞 **Kotlin** — Línguagem utilizada.
- 📱 **Compose** — Desenvolvimento da UI.
- 🚢 **Compose navigation** — Navegação entre telas.
- 🖼️ **Coil** — Renderização de imagens vindas da API.
- 💻 **Retrofit** — Chamada a API.
- 🕛 **Coroutines** — Chamadas assíncronas.
- 💉 **Hilt** — Injeção de dependencias.
- 🎲 **Room** — Armazenamento de dados locais.
- 🧪️ **Junit** Testes unitarios.

## 🚀 Rodando o app

### Pré-requisitos

- Android Studio
- Cadastro no <a href="https://developer.themoviedb.org/docs/getting-started">TMDB</a> para geração de uma API key

### Passo-a-passo

<ol>
  <li>Abra a pasta raiz do projeto no Android Studio.</li>
  <li>Localize o arquivo `local.properties`.</li>
  <li>Adicione a seguinte linha:
  API_KEY=sua_api_key_gerada_no_tmdb.
  </li>
  <li>Selecione a opção `composeApp` na barra superior do Android Studio e aperte o botão play.</li>
  <li>Caso deseje rodar a versão de iOS, selecione a opção `iosApp` na barra superior do Android Studio e aperte o botão play.</li>
</ol>
