import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Product } from '../model/product.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private http = inject(HttpClient);
  list(){
    return this.http.get<Product[]>('http://localhost:8080/api/products');
  }
  get(id: number){
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}`);
  }

  create(product: Product){
    return this.http.post<Product>('http://localhost:8080/api/products',product);
  }

  update (id: number,product: Product){
    return this.http.put<Product>(`http://localhost:8080/api/products/${id}`,product);
  }

  delete (id:number){
    return this.http.delete<void>(`http://localhost:8080/api/products/${id}`);
  }

}
