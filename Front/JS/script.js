/*
  Arquivo: /js/script.js
  Papel: Lógica de front-end, manipulação de DOM e
  preparação para chamadas de API (fetch) para o backend Spring Boot.
  
  Este arquivo não realiza autenticação ou segurança (conforme solicitado).
*/

// Aguarda o DOM estar completamente carregado
document.addEventListener('DOMContentLoaded', () => {

  console.log('Sistema de Registro de Histórias Locais - JS Carregado');

  // Verifica em qual página estamos para executar a lógica correta
  if (document.getElementById('lista-historias')) {
    carregarHistorias();
  }

  if (document.getElementById('form-cadastro')) {
    configurarFormCadastro();
  }

  if (document.getElementById('lista-validacao')) {
    carregarHistoriasParaValidar();
    configurarBotoesValidacao();
  }

  // --- NOVO ---
  // Adiciona o listener para a nova página de detalhes
  if (document.getElementById('detalhe-historia')) {
    carregarDetalheHistoria();
  }
  // --- FIM NOVO ---
});

/**
 * Função para carregar as histórias da API.
 * Será chamada na página historias.html.
 */
async function carregarHistorias() {
  console.log('Tentando carregar histórias...');
  const listaHistorias = document.getElementById('lista-historias');
  if (!listaHistorias) return;

  // ----------------------------------------------------------------
  // TODO: CONEXÃO COM BACKEND (Spring Boot)
  // O código abaixo é mockado. Quando o backend estiver pronto,
  // substitua o 'historiasMock' pela chamada de API real.
  /*
  try {
    const response = await fetch('http://localhost:8080/api/historias');
    if (!response.ok) throw new Error('Falha ao buscar histórias');
    const historias = await response.json();
    
    // Passa os dados reais da API para a função de renderização
    renderizarHistorias(historias);

  } catch (error) {
    console.error('Erro ao carregar histórias:', error);
    listaHistorias.innerHTML = '<p class="text-red-600 col-span-full text-center">Erro ao carregar histórias do servidor.</p>';
  }
  */
  // ----------------------------------------------------------------
  
  // ----- Início do Código Mockado (Substituir) -----
  const historiasMock = [
    { id: 1, titulo: 'A Fundação de Maringá', conteudo: 'Uma breve história sobre os pioneiros e a Companhia Melhoramentos... A cidade foi planejada por Jorge de Macedo Vieira...', cidade: 'Maringá' },
    { id: 2, titulo: 'O Ciclo do Café em Londrina', conteudo: 'Como o café moldou a região norte do Paraná e atraiu migrantes de todo o Brasil e do mundo, criando uma metrópole regional. O solo de terra roxa foi fundamental...', cidade: 'Londrina' },
    { id: 3, titulo: 'Lendas Urbanas de Curitiba', conteudo: 'Conheça a história da Loira Fantasma do bairro Batel, que assombrava taxistas nas noites frias da capital paranaense. Muitos juram tê-la visto...', cidade: 'Curitiba' },
    { id: 4, titulo: 'As Cataratas de Foz', conteudo: 'A descoberta das quedas e o impacto no turismo local e internacional. Santos Dumont teve um papel crucial em tornar a área um parque público...', cidade: 'Foz do Iguaçu' }
  ];
  renderizarHistorias(historiasMock);
  // ----- Fim do Código Mockado -----
}

/**
 * Renderiza a lista de histórias no DOM.
 * @param {Array} historias - Array de objetos de história
 */
function renderizarHistorias(historias) {
  const listaHistorias = document.getElementById('lista-historias');
  listaHistorias.innerHTML = ''; // Limpa a lista

  if (historias.length === 0) {
    listaHistorias.innerHTML = '<p class="text-stone-700 col-span-full text-center">Nenhuma história encontrada.</p>';
    return;
  }

  // Itera sobre as histórias e cria os cards
  historias.forEach(historia => {
    const card = document.createElement('div');
    card.className = 'bg-white p-6 rounded-lg shadow-md transition-shadow duration-300 hover:shadow-xl';
    
    // --- ATUALIZAÇÃO AQUI ---
    // O botão agora é um link <a> estilizado, apontando para 'detalhes.html'
    // com o ID da história como parâmetro de URL (?id=...)
    card.innerHTML = `
      <h3 class="text-xl font-bold text-amber-900 mb-2">${historia.titulo}</h3>
      <p class="text-stone-700 mb-4 line-clamp-3">${historia.conteudo}</p>
      <span class="text-sm text-stone-500 italic mb-4 block">Cidade: ${historia.cidade}</span>
      <a href="detalhes.html?id=${historia.id}" 
         class="w-full text-center block bg-stone-800 text-white py-2 px-4 rounded-md hover:bg-stone-700 transition-colors"
         aria-label="Ver detalhes de ${historia.titulo}">
        Ver Detalhes
      </a>
    `;
    // --- FIM DA ATUALIZAÇÃO ---
    listaHistorias.appendChild(card);
  });
}

// ... (função configurarFormCadastro() permanece a mesma) ...

/**
 * Configura o formulário de cadastro de história.
 * Será chamado na página cadastro.html.
 */
function configurarFormCadastro() {
  const form = document.getElementById('form-cadastro');
  if (!form) return;

  form.addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede o envio tradicional do formulário
    console.log('Formulário de cadastro interceptado.');

    // Coleta os dados do formulário
    const formData = new FormData(form);
    
    // ----------------------------------------------------------------
    // TODO: CONEXÃO COM BACKEND (Spring Boot)
    // O envio de arquivos (imagem/video/audio) usa 'multipart/form-data'.
    // O 'fetch' abaixo está pronto para enviar os dados.
    /*
    try {
      // O 'body' é o próprio formData, o 'headers' não deve setar 'Content-Type'
      // pois o browser fará isso automaticamente para multipart/form-data.
      const response = await fetch('http://localhost:8080/api/historias', {
        method: 'POST',
        body: formData 
      });

      if (response.ok) {
        alert('História cadastrada com sucesso! Ela passará por validação.');
        form.reset();
        window.location.href = 'historias.html';
      } else {
        const erro = await response.text();
        alert(`Erro ao cadastrar história: ${erro}`);
      }
    } catch (error) {
      console.error('Erro na requisição POST:', error);
      alert('Erro de conexão com o backend.');
    }
    */
    // ----------------------------------------------------------------

    // ----- Simulação (Remover) -----
    console.log('Dados (simulados) a enviar para POST /api/historias:');
    for (let [key, value] of formData.entries()) {
      console.log(key, value);
    }
    alert('Simulação: História enviada para o backend!');
    form.reset();
    // -------------------------------
  });
}

// ... (funções carregarHistoriasParaValidar(), renderizarItensValidacao(), configurarBotoesValidacao() e validarHistoria() permanecem as mesmas) ...

async function carregarHistoriasParaValidar() {
  console.log('Carregando histórias para validar...');
  const listaValidacao = document.getElementById('lista-validacao');
  if (!listaValidacao) return;

  // ----------------------------------------------------------------
  // TODO: CONEXÃO COM BACKEND (Spring Boot)
  // Deveria ser uma rota GET que busca histórias com status 'PENDENTE'
  // Ex: const response = await fetch('http://localhost:8080/api/historias?status=pendente');
  // const historias = await response.json();
  // renderizarItensValidacao(historias);
  // ----------------------------------------------------------------

  // ----- Início do Código Mockado (Substituir) -----
  const historiasMock = [
    { id: 4, titulo: 'A Guerra do Contestado', conteudo: 'O conflito social que marcou Santa Catarina...', status: 'PENDENTE' },
    { id: 5, titulo: 'História da minha rua', conteudo: 'Meu avô contou que aqui era tudo mato e só tinha uma capivara...', status: 'PENDENTE' }
  ];
  renderizarItensValidacao(historiasMock);
  // ----- Fim do Código Mockado -----
}

function renderizarItensValidacao(historias) {
  const listaValidacao = document.getElementById('lista-validacao');
  listaValidacao.innerHTML = '';

  if (historias.length === 0) {
    listaValidacao.innerHTML = '<p class="text-stone-700 text-center">Nenhuma história pendente de validação.</p>';
    return;
  }

  historias.forEach(historia => {
    const item = document.createElement('div');
    item.className = 'bg-white p-6 rounded-lg shadow-md mb-4 flex flex-col md:flex-row md:items-center md:justify-between';
    // Adiciona um ID ao elemento pai para remoção fácil
    item.id = `validar-item-${historia.id}`; 
    
    item.innerHTML = `
      <div>
        <h3 class="text-xl font-bold text-amber-900">${historia.titulo}</h3>
        <p class="text-stone-700 mt-2">${historia.conteudo}</p>
      </div>
      <div class="mt-4 md:mt-0 md:ml-4 flex-shrink-0 space-y-2 md:space-y-0 md:space-x-2">
        <button data-id="${historia.id}" data-acao="APROVADO" class="btn-validar w-full md:w-auto bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-500 transition-colors">
          Aprovar
        </button>
        <button data-id="${historia.id}" data-acao="RECUSADO" class="btn-validar w-full md:w-auto bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-500 transition-colors">
          Recusar
        </button>
      </div>
    `;
    listaValidacao.appendChild(item);
  });
}

function configurarBotoesValidacao() {
  const lista = document.getElementById('lista-validacao');
  if (!lista) return;

  // Usa delegação de eventos para capturar cliques nos botões
  lista.addEventListener('click', (event) => {
    // Verifica se o clique foi em um botão com a classe 'btn-validar'
    if (event.target.classList.contains('btn-validar')) {
      const id = event.target.getAttribute('data-id');
      const status = event.target.getAttribute('data-acao');
      
      if (id && status) {
        validarHistoria(id, status);
      }
    }
  });
}

async function validarHistoria(id, status) {
  console.log(`Validando história ${id} como ${status}`);
  
  // ----------------------------------------------------------------
  // TODO: CONEXÃO COM BACKEND (Spring Boot)
  // Aqui será feita a requisição PUT para a API
  /*
  try {
    const response = await fetch(`http://localhost:8080/api/historias/${id}/validar`, {
      method: 'PUT', // Ou PATCH, dependendo da definição da API
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ status: status }) // Envia o novo status
    });

    if (response.ok) {
      alert('Status da história atualizado!');
      // Remove o item da lista visualmente
      document.getElementById(`validar-item-${id}`).remove();
    } else {
      const erro = await response.text();
      alert(`Erro ao validar história: ${erro}`);
    }
  } catch (error) {
    console.error('Erro na requisição PUT:', error);
    alert('Erro de conexão com o backend.');
  }
  */
  // ----------------------------------------------------------------
  
  // ----- Simulação (Remover) -----
  alert(`Simulação: História ${id} foi marcada como ${status}.`);
  // Remove o item da lista visualmente
  const itemParaRemover = document.getElementById(`validar-item-${id}`);
  if (itemParaRemover) {
    itemParaRemover.remove();
  }
  
  // Verifica se a lista ficou vazia
  const listaValidacao = document.getElementById('lista-validacao');
  if (listaValidacao && listaValidacao.children.length === 0) {
     renderizarItensValidacao([]); // Mostra a mensagem de "Nenhuma história"
  }
  // -------------------------------
}


// --- 
// --- 
// --- FUNÇÕES NOVAS PARA A PÁGINA DE DETALHES ---
// --- 

/**
 * Função para carregar os detalhes de UMA história.
 * Será chamada na página detalhes.html.
 */
async function carregarDetalheHistoria() {
  console.log('Tentando carregar detalhes da história...');
  const detalheContainer = document.getElementById('detalhe-historia');
  if (!detalheContainer) return;

  // 1. Pegar o ID da URL
  const params = new URLSearchParams(window.location.search);
  const historiaId = params.get('id');

  if (!historiaId) {
    detalheContainer.innerHTML = '<p class="text-red-600 text-center">Erro: ID da história não fornecido.</p>';
    return;
  }

  // ----------------------------------------------------------------
  // TODO: CONEXÃO COM BACKEND (Spring Boot)
  // O código abaixo é mockado.
  // A rota real será algo como: GET http://localhost:8080/api/historias/{id}
  /*
  try {
    const response = await fetch(`http://localhost:8080/api/historias/${historiaId}`);
    if (!response.ok) throw new Error('Falha ao buscar detalhes da história');
    const historia = await response.json();
    
    // Passa os dados reais da API para a função de renderização
    renderizarDetalhe(historia);

  } catch (error) {
    console.error('Erro ao carregar detalhes:', error);
    detalheContainer.innerHTML = '<p class="text-red-600 text-center">Erro ao carregar história. Ela pode não existir.</p>';
  }
  */
  // ----------------------------------------------------------------
  
  // ----- Início do Código Mockado (Substituir) -----
  // Simula a busca no "banco de dados" mockado
  // No backend, a 'midiaUrl' e 'tipoMidia' viriam da tabela 'Midia'
  // E 'autor' viria do join com 'Usuarios'
  const historiasMockDB = {
    '1': { id: 1, titulo: 'A Fundação de Maringá', conteudo: 'Uma breve história sobre os pioneiros e a Companhia Melhoramentos... A cidade foi planejada por Jorge de Macedo Vieira e se destacou pelo seu traçado urbanístico moderno.\n\nOs primeiros colonizadores enfrentaram muitas dificuldades, mas persistiram na construção de uma das cidades mais verdes do Brasil.', cidade: 'Maringá', autor: 'João da Silva', dataPublicacao: '2024-10-20T10:00:00', midiaUrl: '../assets/img/maringa_antiga.jpg', tipoMidia: 'image' },
    '2': { id: 2, titulo: 'O Ciclo do Café em Londrina', conteudo: 'Como o café moldou a região norte do Paraná e atraiu migrantes de todo o Brasil e do mundo, criando uma metrópole regional.\n\nO solo de terra roxa foi fundamental para o sucesso das plantações, transformando Londrina na "capital mundial do café" por um período.', cidade: 'Londrina', autor: 'Maria Oliveira', dataPublicacao: '2024-10-18T14:30:00', midiaUrl: '../assets/video/londrina_cafe.mp4', tipoMidia: 'video' },
    '3': { id: 3, titulo: 'Lendas Urbanas de Curitiba', conteudo: 'Conheça a história da Loira Fantasma do bairro Batel, que assombrava taxistas nas noites frias da capital paranaense.\n\nMuitos juram tê-la visto e os relatos se espalharam, tornando-se parte do folclore local.', cidade: 'Curitiba', autor: 'Carlos Souza', dataPublicacao: '2024-10-15T09:15:00', midiaUrl: '../assets/audio/lenda_loira.mp3', tipoMidia: 'audio' },
    '4': { id: 4, titulo: 'As Cataratas de Foz', conteudo: 'A descoberta das quedas e o impacto no turismo local e internacional. Santos Dumont teve um papel crucial em tornar a área um parque público, acessível a todos, e não propriedade privada.', cidade: 'Foz do Iguaçu', autor: 'Ana Pereira', dataPublicacao: '2024-10-12T11:45:00', midiaUrl: null, tipoMidia: null } // História sem mídia
  };
  
  const historia = historiasMockDB[historiaId];
  
  if (historia) {
    renderizarDetalhe(historia);
  } else {
    detalheContainer.innerHTML = '<p class="text-red-600 text-center">História não encontrada.</p>';
  }
  // ----- Fim do Código Mockado -----
}

/**
 * Renderiza os detalhes da história no DOM (na página detalhes.html).
 * @param {Object} historia - O objeto da história
 */
function renderizarDetalhe(historia) {
  const detalheContainer = document.getElementById('detalhe-historia');
  detalheContainer.innerHTML = ''; // Limpa o "Carregando..."

  let midiaHtml = '';
  
  // --- Lógica para Mídia ---
  // (O backend deverá fornecer a URL e o tipo de mídia)
  if (historia.midiaUrl) {
    if (historia.tipoMidia === 'image') {
      // Usamos caminhos placeholder, troque por 'historia.midiaUrl'
      midiaHtml = `<img src="../assets/img/placeholder.jpg" alt="${historia.titulo}" class="w-full max-h-96 object-cover rounded-lg mb-6 shadow-md">`;
    } else if (historia.tipoMidia === 'video') {
      midiaHtml = `<video controls class="w-full rounded-lg mb-6 shadow-md">
                        <source src="${historia.midiaUrl}" type="video/mp4">
                        Seu navegador não suporta o player de vídeo.
                       </video>`;
    } else if (historia.tipoMidia === 'audio') {
      midiaHtml = `<audio controls class="w-full mb-6">
                        <source src="${historia.midiaUrl}" type="audio/mpeg">
                        Seu navegador não suporta o player de áudio.
                       </audio>`;
    }
  }

  // --- Metadados ---
  // Formata a data (Ex: 20 de outubro de 2024)
  const dataFormatada = new Date(historia.dataPublicacao).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: 'long',
    year: 'numeric'
  });

  // --- HTML Final ---
  detalheContainer.innerHTML = `
    <h1 class="text-3xl md:text-4xl font-bold text-amber-900 mb-6 text-center">${historia.titulo}</h1>
    
    ${midiaHtml}
    
    <div class="flex flex-col md:flex-row justify-between items-start md:items-center text-stone-600 mb-6 pb-4 border-b border-stone-200 space-y-2 md:space-y-0">
        <span class="font-semibold"><strong>Por:</strong> ${historia.autor}</span>
        <span class="font-semibold"><strong>Local:</strong> ${historia.cidade}</span>
        <span class="font-semibold"><strong>Publicado em:</strong> ${dataFormatada}</span>
    </div>
    
    <div class="prose max-w-none text-stone-800 text-lg leading-relaxed">
        ${historia.conteudo.replace(/\n/g, '<br>')}
    </div>
  `;
}