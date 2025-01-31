document.addEventListener('DOMContentLoaded', function() {
    // Função para fazer uma requisição para o backend
    function fetchData() {
        fetch('/api/data')
            .then(response => response.json())
            .then(data => {
                const contentDiv = document.getElementById('content');
                contentDiv.innerHTML = `<p>Dados recebidos do backend: ${data.message}</p>`;
            })
            .catch(error => console.error('Erro ao buscar dados:', error));
    }

    // Chama a função fetchData ao carregar a página
    fetchData();
});