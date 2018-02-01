import { LocalStorageService } from 'ngx-webstorage';
import { AuthService } from './../auth/auth.service';
import {XHRBackend, Http, RequestOptions} from "@angular/http";
import {InterceptedHttp} from "./http.interceptor";
import { Location } from '@angular/common';

export function httpFactory(xhrBackend: XHRBackend, requestOptions: RequestOptions, storage: LocalStorageService, location: Location): Http {
    return new InterceptedHttp(xhrBackend, requestOptions, storage, location);
}
