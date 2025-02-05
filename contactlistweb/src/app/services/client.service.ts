import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Client } from '../model/client.interface';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private http = inject(HttpClient);
  list(){
    return this.http.get<Client[]>('http://localhost:5174/api/Cliente');
  }
  getFromList(id: number){
    return this.http.get<Client>(`http://localhost:5174/api/Cliente/${id}`);
  }

  postClient(client: Client){
    return this.http.post<Client>('http://localhost:5174/api/Cliente',client);
  }

  putClient (id: number,client: Client){
    return this.http.put<Client>(`http://localhost:5174/api/Cliente/${id}`,client);
  }

  deleteFromList (id:number){
    return this.http.delete<void>(`http://localhost:5174/api/Cliente/${id}`);
  }

}
