import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { ProductService } from '../services/product.service.service';
import { Product } from '../model/product.interface';

@Component({
  selector: 'app-product-form',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css'
})
export default class ProductFormComponent implements OnInit  {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private productService = inject(ProductService);

  form?: FormGroup;
  product?: Product;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    var methodType="POST";
    if (id){
      methodType="PUT";
      this.productService.get(parseInt(id)).subscribe(product => {
        this.product = product;
        this.form=this.fb.group({
          codigo:[product.codigo,[Validators.required]],
          name:[product.name, [Validators.required]],
          precio: [product.precio, [Validators.required]],
          cantidad: [product.cantidad, [Validators.required]]
        })
        console.log('c',product);
      })
    }else{
      this.form=this.fb.group({
        codigo:['',[Validators.required]],
          name:['', [Validators.required]],
          precio: ['', [Validators.required]],
          cantidad: ['', [Validators.required]]
      })
    }
}

save() {
  const productform = this.form!.value;
  if(this.product){
      this.productService.update(this.product.id,productform).subscribe(() => {
        this.router.navigate(['/products']);
      });
  }else{
    this.productService.create(productform).subscribe(() => {
      this.router.navigate(['/products']);
    });
  }
  if (this.form!.invalid) {
    this.form!.markAllAsTouched(); // Marca todos los campos como tocados para que muestren errores
    return;
  }
  console.log(this.form!.value);
}
}
