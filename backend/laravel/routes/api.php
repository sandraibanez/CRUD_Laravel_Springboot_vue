<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\MesaController;


Route::resource('mesa', MesaController::class);
// rutas para mesa
Route::get("/mesa/{id}", [MesaController::class,'show']);
Route::get('/mesa', [MesaController::class,'index']);
// Route::post('/mesa/mesa_new', [MesaController::class,'store']);
// Route::put('/mesa/mesa_mod/{id}', [MesaController::class,'update']);
Route::delete('/mesa/delete/{id}', [MesaController::class,'destroy']);
// rutas para categorias
Route::resource('category', CategoryController::class);
