import { Component, inject, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Client } from '../model/client.interface';

@Component({
  selector: 'app-client-list',
  imports: [DatePipe, RouterModule],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css'
})
export default class ClientListComponent implements OnInit {
  private clientService=inject(ClientService)
  
    clients: Client[] =[];
  
    loadAll(){
      this.clientService.list().subscribe(clients => {
          this.clients=clients
        }
      )
    }
  
    ngOnInit(): void {
      this.loadAll();
    }
  
  
    deleteContact(client: Client){
      this.clientService.deleteFromList(client.idCliente).subscribe(()=>{
        this.loadAll();
        console.log("Elemento eliminado")
      })
    }
}
