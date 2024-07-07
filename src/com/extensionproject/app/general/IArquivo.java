package com.extensionproject.app.general;

import java.io.File;
import java.util.List;

public interface IArquivo<T> {
    public abstract void abrirArquivo(String filename, String extension);
    public abstract void gravarArquivo(T ftype);
    public abstract void fecharArquivo();
}
