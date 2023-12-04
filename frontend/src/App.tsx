import React from 'react';
import logo from './logo.svg';
import './App.scss';
import { Header } from './components/Header';
import { CatalogPage } from './pages/CatalogPage'
import { ProductPage } from './pages/ProductPage'
import { UpdatePage } from './pages/UpdatePage'
import { FormPage } from './pages/FormPage'
import { MapPage } from './pages/MapPage'
import { StatsPage } from './pages/StatsPage'
import { Route, Routes } from 'react-router-dom'
import { SecondPage } from './pages/SecondPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<CatalogPage />} />
      <Route path="/product/:productId" element={<ProductPage />} />
      <Route path="/update/:productId" element={<UpdatePage />} />
      <Route path="/add" element={<FormPage />} />
      <Route path="/map" element={<MapPage />} />
      <Route path="/stat" element={<StatsPage />} />
      <Route path="/second" element={<SecondPage />} />
    </Routes>
  );
}

export default App;
