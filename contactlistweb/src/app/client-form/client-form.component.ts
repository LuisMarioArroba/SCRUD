import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { ClientService } from '../services/client.service';
import { Client } from '../model/client.interface';

@Component({
  selector: 'app-client-form',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.css'
})
export default class ClientFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private clientService = inject(ClientService);

  form?: FormGroup;
  client?: Client;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    var methodType="POST";
    if (id){
      methodType="PUT";
      this.clientService.getFromList(parseInt(id)).subscribe(client => {
        this.client = client;
        this.form=this.fb.group({
          idCliente:[client.idCliente,[Validators.required]],
          nameCliente:[client.nameCliente, [Validators.required]],
          registroCliente:[client.registroCliente, [Validators.required]],
          emailCliente:[client.emailCliente, [Validators.required]],
          userCliente: [client.userCliente, [Validators.required]],
          passCliente: ['', [Validators.required]]
        })
        console.log('c',client);
      })
    }else{
      this.form=this.fb.group({
        idCliente:['',[Validators.required]],
        nameCliente:['', [Validators.required]],
        registroCliente:['', [Validators.required]],
        emailCliente:['', [Validators.required]],
        userCliente: ['', [Validators.required]],
        passCliente: ['', [Validators.required]]
      })
    }
  }

  save() {
    const clientform: Client = this.form!.value;
    clientform.registroCliente = this.formatDate(clientform.registroCliente);
    if(this.client){
      this.clientService.putClient(this.client.idCliente,clientform).subscribe({
        next: () => {
          this.router.navigate(['/clients']);
        },
        error: (err) => {
          console.error('Error al crear cliente:', err);
          alert('Hubo un error al crear el cliente. Inténtalo nuevamente.');
        }
      });
  }else{
    clientform.idCliente=1;
    console.log(clientform);
      this.clientService.postClient(clientform).subscribe({
        next: () => {
          this.router.navigate(['/clients']);
        },
        error: (err) => {
          console.error('Error al crear cliente:', err);
          alert('Hubo un error al crear el cliente. Inténtalo nuevamente.');
        }
      });
  }
    if (this.form!.invalid) {
      this.form!.markAllAsTouched(); // Marca todos los campos como tocados para que muestren errores
      return;
    }
    console.log(this.form!.value);
  }
  private formatDate(date: string): string {
    const d = new Date(date); // Convierte la fecha en Date
    const year = d.getFullYear();
    const month = (d.getMonth() + 1).toString().padStart(2, '0');
    const day = d.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`; // Devuelve la fecha como string
  }
}
