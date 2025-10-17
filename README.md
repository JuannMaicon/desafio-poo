# 🏠 Desafio POO

Este projeto foi desenvolvido com o objetivo de aplicar os **três pilares da POO** — herança, encapsulamento e polimorfismo — em um sistema simples de **corretora imobiliária**.

---

## 🎯 Objetivo
O sistema simula o gerenciamento de **casas e apartamentos** disponíveis para aluguel em uma imobiliária, permitindo:
- Cadastrar, listar e remover imóveis  
- Alugar e disponibilizar imóveis  
- Calcular o valor total de aluguel para um período específico  
- Exibir informações do proprietário e status do imóvel  

---

## 🧩 Estrutura do Projeto
O projeto está organizado em **camadas**, seguindo boas práticas de arquitetura:

```
MAIN → SERVICE → REPOSITORY → DOMINIO
```

### **1. Dominio **
Contém as classes principais do domínio:
- `Imovel` *(classe abstrata)*  
- `Casa` *(subclasse de Imovel)*  
- `Apartamento` *(subclasse de Imovel)*  
- `Proprietario` *(composição com Imovel)*  

Cada imóvel possui um proprietário, endereço, número, valor de aluguel e status (alugado ou disponível).

### **2. Repository **
Responsável por armazenar e gerenciar os imóveis em memória usando `Map<Integer, Imovel>`.

### **3. Service **
Camada que implementa as regras de negócio, como:
- Cadastrar imóveis  
- Alugar ou disponibilizar imóveis  
- Calcular o aluguel  
- Listar imóveis e alugados  

### **4. Main **
Camada de interface no **console**, permitindo interação com o usuário via `Scanner`.

---

## ⚙️ Funcionalidades Principais
✅ Cadastro de casas e apartamentos  
✅ Listagem completa de imóveis  
✅ Aluguel e disponibilização de imóveis  
✅ Cálculo do valor total do aluguel com descontos progressivos:  
- 5% para contratos ≥ 12 meses  
- 10% para contratos ≥ 24 meses  
- 15% para contratos ≥ 36 meses  

✅ Exibição de informações do proprietário  
✅ Aplicação dos conceitos:
- **Herança:** `Casa` e `Apartamento` herdam de `Imovel`  
- **Polimorfismo:** método `estaAlugado()` retorna mensagens específicas  
- **Encapsulamento:** uso de `protected`, `private` e getters/setters controlados  
- **Composição:** `Imovel` contém um objeto `Proprietario`  

---

## 🧱 Diagrama UML
Abaixo está o **Diagrama de Classes** do sistema, representando todas as relações entre as classes:

![Pré-visualização do Diagrama UML](./img-uml.png)

📎 [Versão em PDF do Diagrama UML](./uml-desafio-poo.drawio.pdf)

---

## 🧠 Conceitos Avaliados
| Conceito | Descrição |
|-----------|------------|
| **Herança** | Classes filhas (`Casa`, `Apartamento`) estendem `Imovel`. |
| **Polimorfismo** | Sobrescrita do método `estaAlugado()` com mensagens diferentes. |
| **Encapsulamento** | Atributos `protected/private` e métodos públicos controlados. |
| **Lógica** | Controle de cadastro, exclusão e cálculo de aluguel. |
| **Organização** | Código estruturado em pacotes por responsabilidade. |
| **Criatividade** | Aplicação de descontos automáticos e composição com `Proprietario`. |

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **IDE IntelliJ IDEA**
- **Paradigma Orientado a Objetos (POO)**

---
