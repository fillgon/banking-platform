# Banking Platform

## Sobre o projeto

A Banking Platform é um projeto de estudos desenvolvido para simular uma plataforma bancária moderna baseada em microsserviços.

O objetivo é reproduzir desafios encontrados em instituições financeiras e fintechs, aplicando boas práticas de engenharia de software, arquitetura distribuída, computação em nuvem, observabilidade e integração entre serviços.

Ao longo do desenvolvimento serão implementados serviços independentes, comunicação assíncrona por eventos, infraestrutura conteinerizada e recursos normalmente encontrados em ambientes de produção.

## Objetivos

* Desenvolver uma plataforma bancária baseada em microsserviços.
* Aplicar princípios de Clean Architecture e SOLID.
* Utilizar comunicação síncrona e assíncrona entre serviços.
* Explorar serviços da AWS em ambiente local e em nuvem.
* Implementar observabilidade com métricas, logs e monitoramento.
* Construir uma arquitetura escalável, resiliente e de fácil manutenção.

## Arquitetura prevista

A plataforma será composta por microsserviços independentes, responsáveis por diferentes domínios do sistema bancário.

Os serviços serão desenvolvidos de forma desacoplada e integrados por APIs REST e mensageria baseada em eventos, permitindo evolução independente e maior escalabilidade.

Entre os serviços previstos estão:

* Account Service
* Transaction Service
* Notification Service
* Statement Service
