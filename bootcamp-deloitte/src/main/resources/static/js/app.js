function usuarioApp() {
      const API_BASE = '/usuarios';

      return {
        usuarios: [],
        carregando: false,
        editando: false,
        usuarioParaDeletar: null,
        paginaAtual: 1,
        porPagina: 7,
        form: { id: null, nome: '', email: '', cpf: '', telefone: '' },
        erros: {},
        toast: { visivel: false, mensagem: '', tipo: 'sucesso' },

        // Cordena a paginação da lista de usuarios
        get totalPaginas() {
            return Math.ceil(this.usuarios.length / this.porPagina);
        },

        get usuariosPaginados() {
            const inicio = (this.paginaAtual - 1) * this.porPagina;
            const fim = inicio + this.porPagina;
            return this.usuarios.slice(inicio, fim);
        },

        // Listar os usuarios do crud
        async listar() {
        this.carregando = true;
        try {
            const res = await fetch(API_BASE);
            if (!res.ok) throw new Error('Erro ao buscar usuários');
            this.usuarios = await res.json();
            this.paginaAtual = 1;
        } catch (e) {
            this.mostrarToast('Erro ao conectar com a API: ' + e.message, 'erro');
        } finally {
                this.carregando = false;
            }
        },

        editar(u) {
            this.form = { ...u };
            this.editando = true;
            window.scrollTo({ top: 0, behavior: 'smooth' });
        },

        // Função que atualiza o usuario com validação
        async atualizar() {
            if (!this.validar()) return;
            this.carregando = true;
            try {
                const res = await fetch(`${API_BASE}/${this.form.id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(this.form)
                });
                if (!res.ok) {
                    const data = await res.json();
                    throw new Error(data.error);
                }
                await this.listar();
                this.resetForm();
                this.editando = false;
                this.mostrarToast('Usuário atualizado com sucesso!', 'sucesso');
            } catch (e) {
                this.mostrarToast(e.message, 'erro');
            } finally {
                this.carregando = false;
            }
        },

        // Aqui fica a funcionalidade para apagar o formulario quando desistir de editar
        cancelarEdicao() {
            this.resetForm();
            this.editando = false;
        },

        // Aqui fica a caixa de mensagem ao excluir um usuario
        confirmarDelete(u) {
            this.usuarioParaDeletar = u;
            new bootstrap.Modal(document.getElementById('modalDelete')).show();
        },

        // Função que deleta um usuario
        async deletar() {
            bootstrap.Modal.getInstance(document.getElementById('modalDelete')).hide();
            this.carregando = true;
            try {
                const res = await fetch(`${API_BASE}/${this.usuarioParaDeletar.id}`, {
                    method: 'DELETE'
                });
                if (!res.ok) {
                    const data = await res.json();
                    throw new Error(data.error);
                }
                await this.listar();
                this.mostrarToast('Usuário excluído com sucesso!', 'sucesso');
            } catch (e) {
                this.mostrarToast(e.message, 'erro');
            } finally {
                this.carregando = false;
            }
        },

        // Cadastrar os usuarios do crud
        async cadastrar() {
          if (!this.validar()) return;
          this.carregando = true;
          try {
            const res = await fetch(API_BASE, {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(this.form)
            });

            if (!res.ok) {
                const data = await res.json();
                throw new Error(data.error);
            }
            await this.listar();
            this.resetForm();
            this.mostrarToast('Usuário cadastrado com sucesso!', 'sucesso');
          } catch (e) {
            this.mostrarToast(e.message, 'erro');
          } finally {
            this.carregando = false;
          }
        },

        // Validação dos campos
        validar() {
          this.erros = {};
          if (!this.form.nome?.trim())     this.erros.nome     = 'Nome é obrigatório.';
          if (!this.form.email?.trim())    this.erros.email    = 'E-mail é obrigatório.';
          if (!this.form.cpf?.trim())      this.erros.cpf      = 'CPF é obrigatório.';
          if (!this.form.telefone?.trim()) this.erros.telefone = 'Telefone é obrigatório.';
          return Object.keys(this.erros).length === 0;
        },

        // Reseta o form quando clica em cancelar
        resetForm() {
          this.form = { id: null, nome: '', email: '', cpf: '', telefone: '' };
          this.erros = {};
        },

        // Mostra as mensagens de erro
        mostrarToast(mensagem, tipo = 'sucesso') {
          this.toast = { visivel: true, mensagem, tipo };
          setTimeout(() => this.toast.visivel = false, 6000);
        },

        // Mascara para mostrar na lista de usuario o cpf formatado 123.456.789-00
        formatarCPF(v) {
            return v.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
        },

        // Mascara para mostrar na lista de usuario o telefone formatado
        formatarTelefone(v) {
            if (v.length === 11)
                return v.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
            return v.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');
        },
      };
    }