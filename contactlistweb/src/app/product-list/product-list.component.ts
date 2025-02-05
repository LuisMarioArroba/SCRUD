import { Component, inject, OnInit } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Product } from '../model/product.interface';
import { ProductService } from '../services/product.service.service';

@Component({
  selector: 'app-product-list',
  imports: [CurrencyPipe, RouterModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export default class ProductListComponent implements OnInit{
  private ProductService=inject(ProductService);

  products: Product[] = [];

  loadAll(){
      this.ProductService.list().subscribe(products => {
          this.products=products
        }
      )
    }
  
    ngOnInit(): void {
      this.loadAll();
    }
  
  
    deleteProduct(product: Product){
      this.ProductService.delete(product.id).subscribe(()=>{
        this.loadAll();
        console.log("Elemento eliminado")
      })
    }
}
