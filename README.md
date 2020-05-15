# Cielo Zero Auth [![Build Status](https://travis-ci.com/DeveloperCielo/Zero-Auth.svg?branch=master)](https://travis-ci.com/DeveloperCielo/Zero-Auth) [![Download](https://api.bintray.com/packages/braspag/zero-auth/zero-auth/images/download.svg) ](https://bintray.com/braspag/zero-auth/zero-auth/_latestVersion)

O **Zero Auth** é uma ferramenta de validação de cartões da API Cielo. A validação permite que o lojista saiba se o cartão é valido ou não antes de enviar a transação para autorização, antecipando o motivo de uma provável não autorização.

O **Zero Auth** pode ser usado de 2 maneiras:

- **Padrão:** Envio de um cartão padrão, sem tokenização ou analises adicionais
- **Com cartão Tokenizado:** Envio de um TOKEN 3.0 para analise

É importante destacar que o Zero Auth **não retorna ou analisa** os seguintes itens:

- Limite de crédito do cartão
- Informações sobre o portador
- Não aciona a base bancaria (dispara SMS so portador)

## Instalação

### Dependências

Adicione esta dependência ao *build.gradle* do seu módulo dentro do nó dependencies 

```groovy
dependencies {
    // ...
    implementation 'br.com.cielo:zero-auth:LATEST-VERSION'
}
```

> Não esqueça de verificar se **jcenter()** está configurado como repositório.

- Ou baixe *cielo-bin-query-release.aar* de [releases](https://github.com/DeveloperCielo/zero-auth/releases), mova para a pasta libs do seu módulo e adicione esta dependência ao *build.gradle* do seu módulo dentro do nó dependencies

```groovy
dependencies {
    // ...
    implementation files('libs/zero-auth-release.aar')
}
```

### Internet Permission

É necessário ter adicionado ao **AndroidManifest.xml** do seu módulo a seguinte permissão:

```xml
    <uses-permission android:name="android.permission.INTERNET" />
```

## Utilização

### Inicialização

É necessário ter uma instância de **ZeroAuth**

```kotlin
val zeroAuth = ZeroAuth(
    merchantId = "MERCHANT-ID",
    clientId = "CLIENT-ID",
    clientSecret = "CLIENT-SECRET",
    environment = Environment.SANDBOX
)
```

### Validação

A validação ocorre através do método **validate**, para isto, basta informar os dados do cartão, OAuth Token e um callback:

```kotlin
zeroAuth.validate(
    zeroAuthRequest = ZeroAuthRequest(
        cardType = CardType.CREDIT_CARD,
        cardNumber = "1111222233334444",
        holder = "Maurici Ferreira Junior",
        expirationDate = "01/2030",
        securityCode = "123",
        saveCard = false,
        brand = "Visa",
        cardOnFile = CardOnFile(
            usage = Usage.FIRST,
            reason = Reason.RECURRING
        )
    )
) {
    // Callback
}
```

> Verifique a listagem de campos da requisição [aqui](https://developercielo.github.io/manual/cielo-ecommerce#requisi%C3%A7%C3%A3o222).
