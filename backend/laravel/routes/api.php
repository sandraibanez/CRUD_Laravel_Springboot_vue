<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\MesaController;
use App\Http\Controllers\UserController;


Route::resource('mesa', MesaController::class);
// rutas para mesa
Route::get("/mesa/{id}", [MesaController::class,'show']);
Route::get('/mesa', [MesaController::class,'index']);
// Route::post('/mesa/mesa_new', [MesaController::class,'store']);
// Route::put('/mesa/mesa_mod/{id}', [MesaController::class,'update']);
Route::delete('/mesa/delete/{id}', [MesaController::class,'destroy']);
// rutas para categorias
Route::resource('category', CategoryController::class);
// rutas para usuario
Route::resource('user', UserController::class);
Route::post('user/user_new', [UserController::class, 'store']);
Route::get('user/all', [UserController::class, 'index']);
Route::get('user/{id}', [UserController::class, 'show']);
Route::put('user/update/{id}', [UserController::class, 'update']);
Route::delete('user/destroy/{id}', [UserController::class, 'destroy']);
Route::post('login', [UserController::class, 'login']);
Route::post('logout', [UserController::class, 'logout']);
Route::get('profile', [UserController::class, 'getUserToken']);
Route::get('isAdmin', [UserController::class, 'isAdmin']);

