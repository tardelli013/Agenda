package br.com.tardelli.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.tardelli.dao.AlunoDAO;
import br.com.tardelli.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();
                AlunoDAO alunoDAO = new AlunoDAO(this);

                if (aluno.getId() != null)  {
                    alunoDAO.altera(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno: " + aluno.getNome() + " alterado!", Toast.LENGTH_SHORT).show();
                } else {
                    alunoDAO.insere(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno: " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                }

                alunoDAO.close();


                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
