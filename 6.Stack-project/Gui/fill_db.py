
import requests


data = [
    {
	"url": "http://home.com",
	"htmlPage": "\n\nBEM-VINDO AO SEU NAVIGADOR PERSONALIZADO!\n\n\nO QUE É ISSO?\n\nEste é o seu navegador personalizado, criado com amor e cuidado usando Python e Ttkbootstrap. Sim, você leu bem, Python! A linguagem de programação que torna tudo mais fácil e divertido.\n\nCOMO FUNCIONA?\n\nMas, por trás das cenas, há um backend poderoso feito em Java com SpringBoot, que armazena todo o seu histórico de visitas e páginas padrões. É como um detective que registra todos os seus passos na internet!\n\nE O ARMazenAMENTO?\n\nAgora, aqui vem a parte mais interessante! O armazenamento é feito em uma estrutura de dados personalizada, criada por mim, baseada em uma pilha (estrutura de dados). Sim, você leu bem, uma pilha! É como uma torre de pratos, onde cada prato representa uma informação, e você pode adicionar ou remover pratos à medida que navega pela internet.\n\nCOMO ISSO FUNCIONA NA PRÁTICA?\n\nImagine que cada vez que você visita uma página, um novo prato é adicionado à pilha. Quando você volta para a página anterior, o prato é removido da pilha. É como se você estivesse criando um histórico de visitas em uma torre de pratos!\n\nO QUE VOCÊ PODE FAZER AQUI?\n\n    Navegar pela internet com estilo e personalidade\n    Visualizar seu histórico de visitas em uma torre de pratos (ou seja, a pilha de armazenamento)\n    Acessar páginas padrões personalizadas\n    E muito mais!\n\nOBRIGADO POR USAR NOSSO NAVIGADOR!\n\nEsperamos que você se divirta navegando pela internet com o nosso navegador personalizado. Se tiver alguma dúvida ou precisar de ajuda, não hesite em entrar em contato conosco.\n\nATÉ LOGO!\n\nE lembre-se, com este navegador, você está sempre um passo à frente na internet!\n\nLINKS ÚTEIS\n\n    Não tem '-'\n\nREDENÇÃO DE CULPA\n\nSe você encontrar algum erro ou bug, por favor, não se preocupe! Estamos trabalhando constantemente para melhorar o nosso navegador. E se você tiver alguma sugestão, não hesite em nos contar!\n"
    },
    {
        "url": "http://notfound.com",
        "htmlPage": "\n\nERRO 404: PÁGINA NÃO ENCONTRADA\n\nOOPS! ALGO DEU ERRADO!\n\nParece que você entrou em um beco sem saída! A página que você está procurando não existe ou foi removida. Não se preocupe, isso acontece com o melhor de nós!\n\nO QUE VOCÊ PODE FAZER AGORA?\n\n    Verifique o endereço: Certifique-se de que o endereço da página esteja correto. Talvez você tenha digitado algo errado?\n    Volte para a página inicial: Clique no botão 'Voltar' do seu navegador ou clique aqui para voltar para a página inicial.\n    Procure novamente: Tente procurar por outras palavras-chave ou use o nosso menu de navegação para encontrar o que você está procurando.\n\nUM POUCO DE HUMOR PARA ALEGRAR O DIA\n\nErro 404: a página que você está procurando é como um Pokémon raro - não existe!\n\nOBRIGADO POR VISITAR NOSSO SITE!\n\nEsperamos que você encontre o que está procurando em breve.\n\nATÉ LOGO!"
    },
    {
    "url": "http://google.com",
    "htmlPage": "\n\nGOOGLE.COM\n\nAqui você pode buscar por qualquer coisa na internet, desde respostas para perguntas até imagens e vídeos. O Google é um motor de busca que oferece resultados precisos e relevantes para suas buscas. Você pode digitar palavras-chave ou frases e o Google irá retornar uma lista de resultados que correspondem à sua busca. Além disso, o Google oferece recursos adicionais, como sugestões de busca e links para outras fontes de informações.\n\nO Google é um motor de busca confiável e respeitado, com uma equipe de especialistas que trabalham para garantir que os resultados sejam precisos e relevantes. Você pode usar o Google para encontrar respostas para perguntas, aprender sobre novos tópicos ou simplesmente se divertir. Além disso, o Google oferece recursos adicionais, como o Google Imagens e o Google Vídeos, que permitem que você busque por imagens e vídeos específicos."
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
