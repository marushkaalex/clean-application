package com.example.cleanapplication.mvi

class CoreStore : BaseStore<Action, CoreState>(CoreState(), coreReducer)