
import requests


data = [
    {
	"url": "http://home.com",
	"htmlPage": "\n\nESSA É A HOME PAGE DO NAVIGATOR!"
    },
    {
        "url": "http://notfound.com",
        "htmlPage": "\n\nERRO 404: PÁGINA NÃO ENCONTRADA\n\nOOPS! ALGO DEU ERRADO!\nParece que você entrou em um beco sem saída! A página que você está procurando não existe ou foi removida. Não se preocupe, isso acontece com o melhor de nós!\nO QUE VOCÊ PODE FAZER AGORA?\n    Verifique o endereço: Certifique-se de que o endereço da página esteja correto. Talvez você tenha digitado algo errado?    Volte para a página inicial: Clique no botão 'Voltar' do seu navegador ou clique aqui para voltar para a página inicial.    Procure novamente: Tente procurar por outras palavras-chave ou use o nosso menu de navegação para encontrar o que você está procurando.\nUM POUCO DE HUMOR PARA ALEGRAR O DIA\nErro 404: a página que você está procurando é como um Pokémon raro - não existe!\nOBRIGADO POR VISITAR NOSSO SITE!\nEsperamos que você encontre o que está procurando em breve.\nATÉ LOGO!"
    },
    {
    "url": "https://www.google.com",
    "htmlPage": "Aqui você pode buscar por qualquer coisa na internet, desde respostas para perguntas até imagens e vídeos. O Google é um motor de busca que oferece resultados precisos e relevantes para suas buscas. Você pode digitar palavras-chave ou frases e o Google irá retornar uma lista de resultados que correspondem à sua busca. Além disso, o Google oferece recursos adicionais, como sugestões de busca e links para outras fontes de informações.\n\nO Google é um motor de busca confiável e respeitado, com uma equipe de especialistas que trabalham para garantir que os resultados sejam precisos e relevantes. Você pode usar o Google para encontrar respostas para perguntas, aprender sobre novos tópicos ou simplesmente se divertir. Além disso, o Google oferece recursos adicionais, como o Google Imagens e o Google Vídeos, que permitem que você busque por imagens e vídeos específicos."
    }
]

for i in range(len(data)):
    try:
        response = requests.post("http://localhost:8080/api/page", json=data[i])
        if (response.status_code == 201) | (response.status_code == 200):
            print("Foi adicionado uma nova página")
        else:
            print(
                f"Status Code: {response.status_code}.",
                "Erro"
            )
    except Exception as e:
        print(f"Ocorreu um erro: {e}", "Erro")
